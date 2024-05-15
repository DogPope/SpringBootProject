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
  "composer_id": 8,
  "first_name": "Daniel",
  "last_name": "Jameson",
  "country": "IRELAND",
  "genre": "LITURGICAL",
  "gender": "MALE",
  "year_of_birth": "1993-07-01",
  "year_of_death": "2025-08-12"
}'
echo

# Theres obviously some fucking around going on here with IDs.
#curl -k -X 'PUT' "${uri}/api/composers/5" -H 'accept: */*' -H 'Content-Type: application/json' \
#-d '{
#  "first_name": "Daniel",
#  "last_name": "Jameson",
#  "country": "IRELAND",
#  "genre": "LITURGICAL",
#  "gender": "MALE",
#  "year_of_birth": "1993-07-01",
#  "year_of_death": "2025-08-12"
#}'
#echo