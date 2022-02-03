const http = require('http');

const server = http.createServer((req, res)=>{
    const url = req.url;
    const method = req.method;
    const body = [];

    if(url === '/'){
        res.setHeader('Content-Type', 'text/html');
        res.write('<html>');
        res.write('<head><title>Dashboard</title></head>');
        res.write('<body>Hello User, Welcome to My Page<br><br><form method="POST" action="/create-user"><input type="text" name="username"></input><button type="submit">Add User</button></form></br></br></body>');
        res.write('</html>');
        return res.end();
    }
    if(url === '/users'){
        res.setHeader('Content-Type', 'text/html');
        res.write('<html>');
        res.write('<head><title>Users</title></head>');
        res.write('<body><ul><li>Dummy User</li></ul></body>');
        res.write('</html>');
        return res.end();
    }
    if(url === '/create-user' && method === 'POST'){
        req.on('data',(chunk)=>{
            console.log(chunk);
            body.push(chunk);
        })

        req.on('end', ()=>{
            const parsedBody = Buffer.concat(body).toString();
            const inputData = parsedBody.split("=")[1];
            console.log(inputData);
        })
      
        res.statusCode = 302;
        res.setHeader('Location', '/');
        res.end();
    }
     
});

server.listen(3300);