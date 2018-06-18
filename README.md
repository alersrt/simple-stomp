# Simple STOMP application

This application gets string via websocket, reverses it, saves and returns all reversed strings.

## Endpoints

- Websocket endpoint is `/websocket`
- Requests have to sended to `/request`
- Answers should be readed from `/answer`

## Toolchain

For convenient work with this project [Makefile](Makefile) was created. Available commands is given below:
- `make clear` — clear temporary files;
- `make test` — run unit tests;
- `make build` — build jar-file;
- `make docs` — generate JavaDoc;
- `make run` — run application from sources via `bootRun` command.

All commands are using of docker `gradle:alpine` image.

## Requirements

- Here is used Lombok Library. For make your IDEs supports it, you should to go at the [site][1], select `Install` menu, choose your IDE in `IDE` section and follows instructions.
- Used version of Gradle must be 4.6 or higher.



[1]: https://projectlombok.org/