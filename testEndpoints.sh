##!/usr/bin/env
# Bash script that tests the applications endpoints.
uri=http://localhost:8080

curl -k -X 'GET' "${uri}/api/composers/1" -H 'accept: */*' -H 'Content-Type: application/json'
echo
curl -k -X 'GET' "${uri}/api/composers/2" -H 'accept: */*' -H 'Content-Type: application/json'
echo

###
#curl -k -X 'PUT' "${uri}/api/composers/2" -H 'accept: */*' -H 'Content-Type: application/json' -d '{
#"composerId": 2,
#"firstName": "Claude",
#"lastName": "deBussy,
#"country": "FRANCE",
#"gender": "FEMALE",
#"dateOfBirth":"1862-08-22T00:00:00.000+00:00",
#"dateOfDeath":"1918-03-24T23:00:00.000+00:00"
#}'
echo
###