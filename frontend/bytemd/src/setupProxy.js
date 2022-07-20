const proxy = require('http-proxy-middleware');
module.exports = function (app) {
    app.use(proxy('/v4', {
        target: 'http://localhost:9333/',
        // target: 'http://application.serverless.jansora.com/',
        // target: 'http://jansora.com:9003/',
        // target: 'https://jansora.com/',
        changeOrigin: true,
    }));
};
