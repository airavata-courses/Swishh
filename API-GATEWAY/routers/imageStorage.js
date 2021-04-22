var express = require('express');
var config = require('../config');
var apiAdapter = require('./apiAdapter')
var router = express.Router();

const { routeURLS: {imageStorage}} = config;

const api = apiAdapter(imageStorage);

//files
router.get('/files', (req, res) => {
    api.get(req.path).then(resp => {
        console.log(req.path)
        res.send(resp.data);
    }).catch(function (error){
        console.log(error);
    });
});


router.post('/upload', (req, res) => {
    console.log(req.body);
    api.post(req.path,req.body).then(resp => {
        console.log(resp.body)
        res.send(resp.data);
    }).catch(function (error){
        console.log(error);
    });
});


router.get('/share', (req, res) => {
    api.get(req.path).then(resp => {
        res.send(resp.data);
    });
});



module.exports = router;
