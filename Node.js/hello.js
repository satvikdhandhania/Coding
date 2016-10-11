var http = require('http');
http.createServer(function(request,response){
response.writeHead(200);
response.writeBody("Hello Dog");
response.end();
}).listen(8080);
console.log('Listening to port 8080');
