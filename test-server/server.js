const http = require("http");

const server = http.createServer();

server.on("request", function (req, res) {
  res.writeHead(200, { "Content-Type": "text/plain" });
  res.write("Hello");
  res.end();
});

server.listen(8080, "127.0.0.1");
console.log("server listen...");