# Instant Fish

[![Modrinth](https://img.shields.io/modrinth/game-versions/instant-fish?label=game%20versions)](https://modrinth.com/mod/instant-fish)
[![Modrinth](https://img.shields.io/modrinth/downloads/instant-fish?label=downloads)](https://modrinth.com/mod/instant-fish)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

Fishing rods catch instantly: no waiting, no reeling in.

Cast into water, and the bobber pulls something up on the very next tick. A
full inventory of fish in the time it takes to look away from the screen.

Server-side only. Clients need nothing installed.

## Requirements

| | |
|---|---|
| Minecraft | 26.3 |
| Loader | Fabric 0.19.5+ |
| Java | 25+ |
| Fabric API | not required |
| Environment | server-side (works in singleplayer too) |

## Install

Install on your **server**. Clients do not need the mod, and installing it
client-side does nothing.

<details>
<summary>Manual</summary>

1. Download the jar from [Modrinth](https://modrinth.com/mod/instant-fish).
2. Drop it in the server's `mods/` folder.
3. Restart the server.

</details>

<details>
<summary>Fabric server launcher</summary>

Add it to the mod list on the launcher screen. The server fetches it on start.

</details>

## How it works

The vanilla bite delay (the bobber dipping, then the slow minigame click) is
skipped entirely: the moment the bobber is in water, the catch resolves. Loot
is rolled through the normal path, so fish, treasure, and durability loss all
behave exactly as they do in vanilla — only the wait is gone.

## Building

```sh
./gradlew build
```

The mod jar lands in `build/libs/`. Requires JDK 25.

## Releasing

Push a `v*` tag to the GitHub mirror. CI builds the jar, creates a GitHub
release, and publishes the same jar to Modrinth.

```sh
git tag vX.Y.Z && git push
```

The tag is the single source of truth: `v1.1.0` produces version `1.1.0` on
Modrinth, and the same value is fed to Gradle so the jar filename can't drift
from the published version. A GitHub release is created alongside it with
auto-generated notes.

## Links

- [Modrinth](https://modrinth.com/mod/instant-fish)
- [Source](https://github.com/felix-schindler/mc-instant-fish)
- [Issues](https://github.com/felix-schindler/mc-instant-fish/issues)

## License

MIT — see [LICENSE](LICENSE).
