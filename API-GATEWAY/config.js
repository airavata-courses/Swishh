const config = {
    app : {
          host: "127.0.0.1"
          port: 5000
    },

    routeURLS: {
      userservice : "http://127.0.0.1:5003",
      imageStorage : "http://127.0.0.1:8081",
      sessionManagement : "http://127.0.0.1:8082"
    }
};

module.exports = config;
