# Drones Api

### Documentation
This is a documentation of how to use the drones API, this can be used via curl as below or swagger gui available on endpoint

*[Here](http://localhost:8080/documentation)  ..dev env

or [Here](http://testserverip/documentation)  ..test env (not setup yet)

Using the following online picture for medicine images
https://images.unsplash.com/profile-1446404465118-3a53b909cc82?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=27a346c2362207494baa7b76f5d606e5

### Curl Examples
This document will mainly list curl example transactions. A some dummy data has been added to simplify

1. Create medication entry

          curl -X 'POST' \
           'http://localhost:8080/medication/save' \
           -H 'accept: */*' \
           -H 'Content-Type: application/json' \
           -d '{
           "name": "58HtQFCKxSUBaa",
           "code": "JIEF8SN5LP4T7YU1_AXUNRD2DHKSLFLA1_9HPWK8BR88VIK79LFGJ4YTOT2IG3RF86Q80X",
           "image": "string"
         }'

2. List registered medications

           curl -X 'GET' \
           'http://localhost:8080/medication/list' \
           -H 'accept: */*'

3. Get image for medication by code

            curl -X 'GET' \
            'http://localhost:8080/medication/image/MM2' \
            -H 'accept: image/jpeg'


5. Register a drone

        curl -X 'POST' \
        'http://localhost:8080/drone/register?model=LIGHT_WEIGHT&weightLimit=500&serialNumber=SS1J' \
        -H 'accept: */*' \
        -d ''

6. Set drone state, useful when changing to and from the loading state

        curl -X 'POST' \
        'http://localhost:8080/drone/setstate/SS2?state=LOADING' \
        -H 'accept: */*' \
        -d ''

7. Load medication onto drone

        curl -X 'POST' \
        'http://localhost:8080/drone/loadmedication?droneSerial=SS5&medicationName=not%20set&medicationCode=MM2&weight=19' \
        -H 'accept: */*' \
        -d ''

8. Check loaded medication for a drone


        curl -X 'GET' \
        'http://localhost:8080/drone/checkloaded/SS6' \
        -H 'accept: */*'

9. Check drone information


        curl -X 'GET' \
        'http://localhost:8080/drone/checkdrone/SS6' \
        -H 'accept: */*'

10. List drones

        curl -X 'GET' \
        'http://localhost:8080/drone' \
        -H 'accept: */*'


11. List available drones for loading

        curl -X 'GET' \
        'http://localhost:8080/drone/checkavailable' \
        -H 'accept: */*'


12. Select log of drone activity (mainly battery level for now), show last 10 records


                curl -X 'GET' \
                'http://localhost:8080/log' \
                -H 'accept: */*'

12. Select log of drone activity select specific page (mainly battery level for now)

                curl -X 'GET' \
                'http://localhost:8080/log/2/30' \
                -H 'accept: */*'


