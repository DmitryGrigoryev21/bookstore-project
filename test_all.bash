#!/usr/bin/env bash
#
# Sample usage:
#   ./test_all.bash start stop
#   start and stop are optional
#
#   HOST=localhost PORT=7000 ./test-em-all.bash
#
# When not in Docker
#: ${HOST=localhost}
#: ${PORT=7000}

# When in Docker
: ${HOST=localhost}
: ${PORT=8080}

#array to hold all our test data movie ids
allTestBookUUIDs=()

function assertCurl() {

  local expectedHttpCode=$1
  local curlCmd="$2 -w \"%{http_code}\""
  local result=$(eval $curlCmd)
  local httpCode="${result:(-3)}"
  RESPONSE='' && (( ${#result} > 3 )) && RESPONSE="${result%???}"

  if [ "$httpCode" = "$expectedHttpCode" ]
  then
    if [ "$httpCode" = "200" ]
    then
      echo "Test OK (HTTP Code: $httpCode)"
    else
      echo "Test OK (HTTP Code: $httpCode, $RESPONSE)"
    fi
  else
      echo  "Test FAILED, EXPECTED HTTP Code: $expectedHttpCode, GOT: $httpCode, WILL ABORT!"
      echo  "- Failing command: $curlCmd"
      echo  "- Response Body: $RESPONSE"
      exit 1
  fi
}

function assertEqual() {

  local expected=$1
  local actual=$2

  if [ "$actual" = "$expected" ]
  then
    echo "Test OK (actual value: $actual)"
  else
    echo "Test FAILED, EXPECTED VALUE: $expected, ACTUAL VALUE: $actual, WILL ABORT"
    exit 1
  fi
}

#have all the microservices come up yet?
function testUrl() {
    url=$@
    if curl $url -ks -f -o /dev/null
    then
          echo "Ok"
          return 0
    else
          echo -n "not yet"
          return 1
    fi;
}

#prepare the test data that will be passed in the curl commands for posts and puts
function setupTestdata() {

#movie aggregate with two ratings
  body=\
'{


     "title": "War and Peace",
     "author": "Leo Tolstoy",
     "summary": "War and Peace broadly focuses on Napoleon’s invasion of Russia in 1812 and follows three of the most well-known characters in literature: Pierre Bezukhov, the illegitimate son of a count who is fighting for his inheritance and yearning for spiritual fulfillment; Prince Andrei Bolkonsky, who leaves his family behind to fight in the war against Napoleon; and Natasha Rostov, the beautiful young daughter of a nobleman who intrigues both men. As Napoleon’s army invades, Tolstoy brilliantly follows characters from diverse backgrounds—peasants and nobility, civilians and soldiers—as they struggle with the problems unique to their era, their history, and their culture. And as the novel progresses, these characters transcend their specificity, becoming some of the most moving—and human—figures in world literature.",
     "isbn": "1400079985",
     "price": 19.99,
     "review": [
         {

             "review": "good book",
             "rating": 4
         },
         {

             "review": "ok book",
             "rating": 3
         }
     ],
     "authorDetail": {
         "author": "Leo Tolstoy",
         "bestseller": "War and Peace",
         "bio": "He was a cool dude"
     },
     "tag": [
         {

             "tag": "classic"
         },
         {

             "tag": "long"
         },
         {

             "tag": "deep"
         }
     ]
 }'
    recreateAggregate 1 "$body"

#movie aggregate with one rating


#movie aggregate with no ratings

}

function recreateAggregate() {
    local testId=$1
    local aggregate=$2

    #create the movie aggregates 
    bookUUID=$(curl -X POST http://$HOST:$PORT/api/book -H "Content-Type:
    application/json" --data "$aggregate" | jq '.bookUUID')
    allTestBookUUIDs[$testId]=$bookUUID
    echo "Added Book Aggregate with bookUUID: ${allTestBookUUIDs[$testId]}"
}

#don't start testing until all the microservices are up and running
function waitForService() {
    url=$@
    echo -n "Wait for: $url... "
    n=0
    until testUrl $url
    do
        n=$((n + 1))
        if [[ $n == 100 ]]
        then
            echo " Give up"
            exit 1
        else
            sleep 6
            echo -n ", retry #$n "
        fi
    done
}

#start of test script
set -e

echo "HOST=${HOST}"
echo "PORT=${PORT}"

if [[ $@ == *"start"* ]]
then
    echo "Restarting the test environment..."
    echo "$ docker-compose down"
    docker-compose down
    echo "$ docker-compose up -d"
    docker-compose up -d
fi

#waitForService http://$HOST:${PORT}/api/movies/423
#try to delete a movie that you've set up but that you don't need. This will confirm that things are working
waitForService curl -X DELETE http://$HOST:$PORT/api/book/e74064af-16db-43b5-ad9a-f809ebd56f98

setupTestdata

#Explicit GET Movie Aggregate tests

# Verify that a normal request works, expect two ratings
assertCurl 200 "curl http://$HOST:$PORT/api/book/${allTestBookUUIDs[1]} -s"
assertEqual ${allTestBookUUIDs[1]} $(echo $RESPONSE | jq .bookUUID)


# Verify that a normal request works, expect no ratings
assertEqual 2 $(echo $RESPONSE | jq ".review | length")

# Verify that a 404 (Not Found) error is returned for a non existing movieId (99)
assertCurl 404 "curl http://$HOST:$PORT/api/book/abc -s"

# Verify that a 422 (Unprocessable Entity) error is returned for a movieId that is out of range (-1)


# Verify that a 400 (Bad Request) error error is returned for a movieId that is not a number, i.e. invalid format

#Verify that a normal delete works, use Movie Aggregate from TestData 1 i.e. allTestMovieIds[1]
assertCurl 200 "curl -X DELETE http://$HOST:$PORT/api/book/${allTestBookUUIDs[1]} -s"

assertCurl 404 "curl http://$HOST:$PORT/api/book/${allTestBookUUIDs[1]} -s"





if [[ $@ == *"stop"* ]]
then
    echo "We are done, stopping the test environment..."
    echo "$ docker-compose down"
    docker-compose down
fi