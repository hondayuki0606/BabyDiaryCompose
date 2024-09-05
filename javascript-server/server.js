const express = require('express');
const app = express();

app.use(express.json());
const loggerMiddleware = function(req, res, next) {
  console.log(`[${new Date()}] ${req.method} ${req.url}`);
  next();
};

app.use(loggerMiddleware);
const courses = [
    { id: 1, name: 'computer science'},
    { id: 2, name: 'information technology'},
    { id: 3, name: 'business intelligence'},
];

const users = [
    {
      id: 1,
      user_name: 'めいたん',
      first_name: '名前',
      last_name: '苗字',
      password: 'password',
      user_status: 0
    }
];

const user = { id: 1, user_name: 'ユーザ名', first_name: 'ユーザ名', last_name: 'last_name', last_name: 'last_name', password: 'password', user_status: 0 }

const { validationResult } = require('express-validator');

// 外部ファイル化したバリデーション読み込み
const PostUserValidator = require('./user/postBodyValidator');

app.post('/user', PostUserValidator, (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(422).json({ errors: errors.array() });
    }
    console.log(req.body);
    if(req.body.user_name=='') return res.status(404).send('The user_name is  with the given id was not found.');
    const user = {
      id: users.length + 1,
      user_name: req.body.user_name,
      first_name: req.body.first_name,
      last_name: req.body.last_name,
      password: req.body.password,
      user_status: req.body.user_status
    };
    users.push(user);
    res.send(user);
});

app.get('/user', (req, res) => {
    const user = users.find(c => c.id === parseInt(req.query.id));
    if (!user) return res.status(404).send('The user with the given id was not found.');
    res.send(user);
});

app.get('/user/login', (req, res) => {
    if (req.query.username=='') {
        console.log('userName is empty.');
        return res.status(404).send(false);
    }
    if (req.query.password=='') {
        console.log('password is empty.');
        return res.status(404).send(false);
    }
    console.log(req.query.username);
    console.log(req.query.password);
    res.send(true);
});

app.get('/api/courses', (req, res) => {
    console.log(`req.ip ${req.ip}`);
    res.send(courses);
});

app.get('/api/courses/:id', (req, res) => {
    const course = courses.find(c => c.id === parseInt(req.params.id));
    if (!course) return res.status(404).send('The course with the given ID was not found.');
    res.send(course);
});

app.post('/api/courses', (req, res) => {
    const course = {
        id: courses.length + 1,
        name: req.body.name
    };
    courses.push(course);
    res.send(course);
});

app.put('/api/courses/:id', (req, res) => {
    const course = courses.find(c => c.id === parseInt(req.params.id));
    if (!course) return res.status(404).send('The course with the given ID was not found.');

    course.name = req.body.name;
    res.send(course);
});

app.delete('/api/courses/:id', (req, res) => {
    const course = courses.find(c => c.id === parseInt(req.params.id));
    if (!course) return res.status(404).send('The course with the given ID was not found.');

    const index = courses.indexOf(course);
    courses.splice(index, 1);

    res.send(course);
});

const port = 3000;
app.listen(3000, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});
