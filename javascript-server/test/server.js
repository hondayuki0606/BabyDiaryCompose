const express = require('express');
const app = express();

app.use(express.json());

const courses = [
    { id: 1, name: 'computer science'},
    { id: 2, name: 'information technology'},
    { id: 3, name: 'business intelligence'},
];

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

app.get('/', (req, res) => {
    res.send('Simple REST API');
});

app.get('/api/courses', (req, res) => {
    res.send(courses);
});

app.get('/api/courses/:id', (req, res) => {
    const course = courses.find(c => c.id === parseInt(req.params.id));
    if (!course) return res.status(404).send('The course with the given ID was not found.');
    res.send(course);
});

app.get('/api/posts/:year/:month', (req, res) => {
    res.send(req.query);
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


const port = process.env.PORT || 3000; // process.env.PORTは不要？
app.listen(port, () => console.log(`Listening on port ${port}...`));
