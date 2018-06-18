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
    stompClient.subscribe('/message/reversed', function (reversed) {
      showStrings(reversed.body.content);
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
  stompClient.send("/app/string", {}, JSON.stringify({"string": document.getElementById('input').value}));
  document.getElementById('input').value = '';
  showStrings();
}

function showStrings(array) {
  let messages = array.content;

  ReactDOM.render(
      <div>
        {messages.map(m => <p>{m.string}</p>)}
      </div>,
      document.getElementById('root')
  );
}