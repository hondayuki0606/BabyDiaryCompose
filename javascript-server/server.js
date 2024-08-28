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

//var http = require('http');
//var server = http.createServer();
//
//var fs = require('fs');
//var xml_string;
//
//server.on('request', function (req, res) {
//    if (req.url.indexOf('TestAPI') != -1) {
//        fs.readFile('./routeSearch/stub_200_routesearch_response_body_10.xml', 'utf-8', (err, files) => {
//            if (err) {throw err;}
//            xml_string = files;
//            res.writeHead(200,{'Content-Type':'application/xml'});
//            res.end(xml_string);
//        });
//    }
//});

// パッケージを読み込む
let http = require('http');

// サーバ機能の初期化
let server = http.createServer();

// リクエストがきたときに呼び出される86

server.on('request', function(req, res)
{
    // 本文（Body部）に文字を表示する
    res.write('Hello, world!');
    res.end();
});
// 指定したIPアドレス、ポート番号でサーバを立てる
server.listen(1337, '127.0.0.1');