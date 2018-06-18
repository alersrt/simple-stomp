# Simple STOMP application

This application gets string via websocket, reverses it, saves and returns all reversed strings.

## Endpoints

- Websocket endpoint is `/websocket`
- Requests have to sended to `/app/string`
- Answers should be readed from `/message/reversed`

## Toolchain

For convenient work with this project [Makefile](Makefile) was created. Available commands is given below:
- `make clear` — clear temporary files;
- `make test` — run unit tests;
- `make build` — build jar-file;
- `make docs` — generate JavaDoc.

All commands are using of docker `gradle:alpine` image.