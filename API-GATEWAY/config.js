const config = {
    app : {
          host: "127.0.0.1",
          port: 5000
    },

    routeURLS: {
      userService : "http://userservice:5004",
      imageStorage : "http://imagestorage-service:8081",
      sessionManagement : "http://sessionservice-service:8082"
    }
};

module.exports = config;
