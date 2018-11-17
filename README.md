# Its sprint boot web application project. #

##Running Instruction: ##

1. Open Project in eclipse as maven existing project:
2. Run com.coinbase.job.java as java application


## Problem Statement with Solution ##

● Allowing users to see the bitcoin price movement for last week, last month, last year or any
custom date.

### Solution: ###
API: GET:
* http://localhost:8080/v1/api/bitcoin/get_price?type=last_week
* http://localhost:8080/v1/api/bitcoin/get_price?type=last_month
* http://localhost:8080/v1/api/bitcoin/get_price?type=last_year
* http://localhost:8080/v1/api/bitcoin/get_price?start_date=2018-01-01&&end_date=2018-01-09

#### Valid Example ####

API:
* http://localhost:8080/v1/api/bitcoin/get_price?type=last_week

output:
```
BTCData [data=Data [currency=USD, prices=[Price [price=6355.71, time=Sat Nov 10 05:30:00 IST 2018], Price [price=6366.55, time=Fri Nov 09 05:30:00 IST 2018], Price [price=6451.04, time=Thu Nov 08 05:30:00 IST 2018], Price [price=6506.34, time=Wed Nov 07 05:30:00 IST 2018], Price [price=6413.17, time=Tue Nov 06 05:30:00 IST 2018], Price [price=6405.13, time=Mon Nov 05 05:30:00 IST 2018], Price [price=6365.53, time=Sun Nov 04 05:30:00 IST 2018]]]]
```

#### Invalid Example ####


* API: http://localhost:8080/v1/api/bitcoin/get_price?type=last_weeks

output:
```
{"error":"Unsupported type : supported values are last_week, last_month, last_year","error_description":null}
```

* API: http://localhost:8080/v1/api/bitcoin/get_price?start_date=2018-01-01&&end_date=90-09-56

output:
```
{"error":"Unsupported Date : supported Date format  are yyyy-mm-dd","error_description":null}
```


● Allowing users to see the X days rolling / moving average bitcoin prices between two custom
dates.


### Solution: ###
API: GET:
* http://localhost:8080/v1/api/bitcoin/get_moving_avg?days=10
* http://localhost:8080/v1/api/bitcoin/get_moving_avg?start_date=2018-01-01&&end_date=2018-01-09

#### Valid Example ####

* API: http://localhost:8080/v1/api/bitcoin/get_moving_avg?days=10

Output:
```
{'moving average: 6170.831 '}
```

#### Invalid Example ####

* API: http://localhost:8080/v1/api/bitcoin/get_moving_avg?days=xy

Output:
```
{"error":"Unsupported Days : supported Days are non zero and non negative number","error_description":null}
```


● Allowing users to get bitcoin trading decision on whether to BUY, SELL or HOLD based on the
price movement for the last X days (use any calculation/algorithm which you’d consider optimal to
determine the outcome).

### Solution: ###
API: GET:
* http://localhost:8080/v1/api/bitcoin/get_trade_decision?days=100

#### Valid Example ####

API:
http://localhost:8080/v1/api/bitcoin/get_trade_decision?days=100

Output:
```
{'Trade decision : BUY '}
```

#### Invalid Example ####

* API: http://localhost:8080/v1/api/bitcoin/get_trade_decision?days=yu

Output:
```
{"error":"Unsupported Days : supported Days are non zero and non negative number","error_description":null}
```

* API: http://localhost:8080/v1/api/bitcoin/get_trade_decision?days=4

Output:
```
{"error":"Unsupported Days : Please select days greater than 10 for better result in this algorithm","error_description":null}
```

### Note ###
To get the historic data on bitcoin prices, you can use the following API exposed by coinbase:
https://www.coinbase.com/api/v2/prices/BTC-USD/historic?period=all
Build the application using Scala or Java on top of any web framework of your choice. To submit the
solution, please put your code on Github and share the repo with us. We expect the code / solution
adhering to best coding practice and design patterns.