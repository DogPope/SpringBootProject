##!/usr/bin/env
# Bash script that tests the applications endpoints.
uri=http://localhost:5000

curl -k -X 'GET' "${uri}/api/composers" -H 'accept: */*' -H 'Content-Type: application/json'
echo
curl -k -X 'GET' "${uri}/api/composers/1" -H 'accept: */*' -H 'Content-Type: application/json'
echo
curl -k -X 'GET' "${uri}/api/composers/2" -H 'accept: */*' -H 'Content-Type: application/json'
echo

curl -k -X 'POST' "${uri}/api/composers" -H 'accept: */*' -H 'Content-Type: application/json' \
-d '{
  "composerId": 8,
  "firstName": "Daniel",
  "lastName": "Jameson",
  "country": "IRELAND",
  "genre": "LITURGICAL",
  "gender": "MALE",
  "dateOfBirth": "1993-07-01",
  "dateOfDeath": "2025-08-12"
}'
echo


curl -k -X 'PUT' "${uri}/api/composers/5" -H 'accept: */*' -H 'Content-Type: application/json' \
-d '{
  "firstName": "Daniel",
  "lastName": "Jameson",
  "country": "IRELAND",
  "genre": "LITURGICAL",
  "gender": "MALE",
  "dateOfBirth": "1993-07-01",
  "dateOfDeath": "2025-08-12"
}'
echo