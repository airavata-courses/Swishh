var express = require('express');
var router = express.Router();
var userRouter = require('./userService');
var imageStorageRouter = require('./imageStorage');
var sessionManagementRouter = require('/sessionManagement');


router.use((req, res, next) => {
    console.log("Called", req.path);
    next()
});

router.use(userRouter);
router.use(imageStorageRouter);
router.use(sessionManagementRouter);

module.exports = router;
