let stompClient = null;

function setConnected(connected) {
  document.getElementById('connect').disabled = connected;
  document.getElementById('disconnect').disabled = !connected;
}

function connect() {
  let socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/answer', function (answer) {
      showStrings(JSON.parse(answer.body));
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function send() {
  stompClient.send("/request", {}, JSON.stringify({"string": document.getElementById('input').value}));
  document.getElementById('input').value = '';
  showStrings();
}

function showStrings(array) {
  ReactDOM.render(
      <div>
        {array.map(m => <p id={m.id}>{m.string}</p>)}
      </div>,
      document.getElementById('root')
  );
}