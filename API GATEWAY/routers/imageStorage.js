var express = require('express');
var config = require('../config');
var apiAdapter = require('./apiAdapter')
var router = express.Router();

const { routeURLS: {imageStorage}} = config;

const api = apiAdapter(imageStorage);


router.get('/files', (req, res) => {
    api.get(req.path).then(resp => {
        res.send(resp.data);
    });
});


router.post('/upload', (req, res) => {
    api.post(req.path).then(resp => {
        res.send(resp.data);
    });
});


router.get('/share', (req, res) => {
    api.get(req.path).then(resp => {
        res.send(resp.data);
    });
});



module.exports = router;
