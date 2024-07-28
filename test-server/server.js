//const http = require("http");
//
//const server = http.createServer();
//
//server.on("request", function (req, res) {
//  res.writeHead(200, { "Content-Type": "text/plain" });
//  res.write("Hello");
//  res.end();
//});
//
//server.listen(8080, "127.0.0.1");
//console.log("server listen...");

//var http = require('http');
//var server = http.createServer();
//
//var fs = require('fs');
//var xml_string;
//
//server.on('request', function (req, res)) {
//    if (req.url.indexOf('TestAPI') != -1) {
//        fs.readFile('./routeSearch/stub_200_routesearch_response_body_10.xml', 'utf-8', err, files) => {
//            if (err) {throw err;}
//            xml_string = files;
//            res.writeHead(200,{'Content-Type':'application/xml'});
//            res.end(xml_string);
//        });
//    }
//});
//
//server.listen(3000);

// Your code here!

var http = require('http');
var server = http.createServer();

var fs = require('fs');
var xml_string;

server.on('request', function (req, res) {
    if (req.url.indexOf('TestAPI') != -1) {
        fs.readFile('./routeSearch/stub_200_routesearch_response_body_10.xml', 'utf-8', (err, files) => {
            if (err) {throw err;}
            xml_string = files;
            res.writeHead(200,{'Content-Type':'application/xml'});
            res.end(xml_string);
        });
    }
});

server.listen(3000);