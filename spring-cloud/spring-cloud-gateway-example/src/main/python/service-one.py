from http.server import BaseHTTPRequestHandler, HTTPServer
import json

# Define the HTTP request handler class
class RequestHandler(BaseHTTPRequestHandler):
    def _set_headers(self, status_code=200):
        self.send_response(status_code)
        self.send_header('Content-type', 'application/json')
        self.end_headers()

    # Define the GET method
    def do_GET(self):
        if self.path == '/resource':
            self._set_headers()
            self.wfile.write(b'Hejjj!! - Server 1')
        else:
            self._set_headers(404)
            self.wfile.write(b'Page not found!!! - Server 1')

    # Define the POST method
    def do_POST(self):
        if self.path == '/resource':
            content_length = int(self.headers['Content-Length'])
            post_data = self.rfile.read(content_length)
            try:
                json_data = json.loads(post_data.decode('utf-8'))
                # Here you can handle the JSON data as needed
                # For example:
                response_data = {'message': 'Received JSON data - Server 1', 'data': json_data}
                self._set_headers()
                self.wfile.write(json.dumps(response_data).encode('utf-8'))
            except json.JSONDecodeError:
                self._set_headers(400)
                self.wfile.write(b'Invalid JSON data - Server 1')
        else:
            self._set_headers(404)
            self.wfile.write(b'Page not found!!! - Server 1')

# Define the server address
server_address = ('localhost', 8081)

# Create an instance of HTTPServer with the defined request handler
httpd = HTTPServer(server_address, RequestHandler)

# Start the server
print('Server 1 running on http://localhost:8081')
httpd.serve_forever()
