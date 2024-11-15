##!/usr/bin/env
# Bash script that tests the applications endpoints.
uri=http://localhost:5000/api/

# Get all musicians
curl -k -X 'GET' "${uri}musicians" -H 'accept: */*' -H 'Content-Type: application/json'
echo

# Get composer at /1
curl -k -X 'GET' "${uri}musicians/1" -H 'accept: */*' -H 'Content-Type: application/json'
echo

# Insert composer at position
curl -k -X 'POST' "${uri}musicians" -H 'accept: */*' -H 'Content-Type: application/json' \
-d '{
  "musicianId": 8,
  "firstName": "Daniel",
  "lastName": "Jameson",
  "country": "IRELAND",
  "genre": "LITURGICAL",
  "gender": "MALE",
  "dateOfBirth": "1993-07-01",
  "dateOfDeath": "2025-08-11",
  "instruments":{"PIANO","ACCORDION"}
}'
echo

# Update composer at ID
curl -k -X 'PUT' "${uri}musicians/5" -H 'accept: */*' -H 'Content-Type: application/json' \
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

# Get all compositions
curl -k -X 'GET' "${uri}compositions" -H 'accept: */*' -H 'Content-Type: application/json'
echo

# Get Piece at /1
curl -k -X 'GET' "${uri}compositions/1" -H 'accept: */*' -H 'Content-Type: application/json'
echo

# Insert piece into compositions
curl -k -X 'POST' "${uri}compositions" -H 'accept: */*' -H 'Content-Type: application/json' \
-d '{
  "pieceId": 8,
  "title": "Daniel",
  "year": 1989,
  "composerId": "1"
}'
echo

# Update piece at /5
curl -k -X 'PUT' "${uri}compositions/5" -H 'accept: */*' -H 'Content-Type: application/json' \
-d '{
  "pieceId": 8,
  "title": "Aliens Are Real",
  "year": 2254,
  "composerId": "1"
}'
echo