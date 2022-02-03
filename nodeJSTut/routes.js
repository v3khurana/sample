const fs = require('fs');

const requestHandler = (req, res) => {
    //console.log(req.url , req.method, req.headers);
    const url = req.url;
    const method = req.method;
    if(url === '/'){
        res.write('<html>');
        res.write('<head><title>Enter Message</title></head>');
        res.write('<body><form action="/message" method="POST"><input type="text" name="message"><button type="submit">Click Me</button></form></body>');
        res.write('</html>');    
        return res.end();
    }
    if(url === '/message' && method === 'POST'){
        const body = [];
        //Registering Event Listener with req.on
        req.on('data', (chunk)=>{
            console.log(chunk);
            body.push(chunk);
        });
        
        req.on('end', ()=>{
            const parsedBody = Buffer.concat(body).toString();
            console.log(parsedBody);
            const message = parsedBody.split("=")[1];
            //fs.writeFileSync('message.txt', message);
            fs.writeFile('message.txt', message, (err)=>{
                console.log(err);
                console.log("Logged in case of error");
            });
            
        });
        //res.writeHead(302, {});
        res.statusCode = 302;
        res.setHeader('Location', '/');
        return res.end();
        
    }
    res.setHeader('Content-Type', 'text/html');
    res.write('<html>');
    res.write('<head><title>My First Page</title></head>');
    res.write('<body><h1> Hello From My Node Js Server </h1></body>');
    res.write('</html>');
    res.end();
};

//module.exports = requestHandler;

/* module.exports = {
    handler : requestHandler,
    someText : "Some Hard coded text"
}; */

module.exports.handler = requestHandler;
module.exports.someText = "Some hard coded text";