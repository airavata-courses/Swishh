var express = require('express');
var bodyParser = require('body-parser');

var router = require('./routers/router');
var formidableMiddleware = require('express-formidable')
var config = require('./config');



var app = express();

const {app : {port}} = config;

app.use(bodyParser.json());
app.use(formidableMiddleware());
app.use(bodyParser.urlencoded({extended:True}));

app.get('/', (req,res) => {
    res.send('API Gateway');
});


app.use(router)

console.log('API Gateway is running on port 5000');

app.listen(port);
