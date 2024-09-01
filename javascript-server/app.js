const ngrok = require("ngrok"),
  express = require("express");
const port = 8080;

const app = express();

app.get("/", (req, res) => {
  res.send("Hello World!");
});

ngrok.connect(port).then((url) => {
  app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
    console.log(`Example app listening at ${url}`);
  });
});

