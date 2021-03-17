var express = require('express');
var config = require('../config');
var apiAdapter = require('./apiAdapter')
var router = express.Router();


const { routeURLS: {sessionManagement}} = config;

const api = apiAdapter(sessionManagement);



router.post('/session', (req, res) => {
    api.post(req.path).then(resp => {
        res.send(resp.data);
    });
});


router.post('/validate', (req, res) => {
    api.post(req.path).then(resp => {
        res.send(resp.data);
    });
});


router.post('/invalidate', (req, res) => {
    api.post(req.path).then(resp => {
        res.send(resp.data);
    });
});


module.exports = router
