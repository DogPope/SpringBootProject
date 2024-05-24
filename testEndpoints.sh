##!/usr/bin/env
# Bash script that tests the applications endpoints.
uri=http://localhost:5000/api/

# Get all composers
curl -k -X 'GET' "${uri}composers" -H 'accept: */*' -H 'Content-Type: application/json'
echo

# Get composer at /1
curl -k -X 'GET' "${uri}composers/1" -H 'accept: */*' -H 'Content-Type: application/json'
echo

# Insert composer at position
curl -k -X 'POST' "${uri}composers" -H 'accept: */*' -H 'Content-Type: application/json' \
-d '{
  "composerId": 8,
  "firstName": "Daniel",
  "lastName": "Jameson",
  "country": "IRELAND",
  "genre": "LITURGICAL",
  "gender": "MALE",
  "dateOfBirth": "1993-07-01",
  "dateOfDeath": "2025-08-11"
}'
echo

# Update composer at ID
curl -k -X 'PUT' "${uri}composers/5" -H 'accept: */*' -H 'Content-Type: application/json' \
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

# Get all pieces
curl -k -X 'GET' "${uri}pieces" -H 'accept: */*' -H 'Content-Type: application/json'
echo

# Get Piece at /1
curl -k -X 'GET' "${uri}pieces/1" -H 'accept: */*' -H 'Content-Type: application/json'
echo

# Insert piece into pieces
curl -k -X 'POST' "${uri}pieces" -H 'accept: */*' -H 'Content-Type: application/json' \
-d '{
  "pieceId": 8,
  "title": "Daniel",
  "year": 1989,
  "composerId": "1"
}'
echo

# Update piece at /5
curl -k -X 'PUT' "${uri}pieces/5" -H 'accept: */*' -H 'Content-Type: application/json' \
-d '{
  "pieceId": 8,
  "title": "Aliens Are Real",
  "year": 2254,
  "composerId": "1"
}'
echo