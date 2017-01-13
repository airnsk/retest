# re2

A [re-frame](https://github.com/Day8/re-frame) application designed to ... well, that part is up to you.

## Development Mode

### Run application:

create file src/cljs/re2/config.cljs

(ns re2.config)

(def debug?
  ^boolean js/goog.DEBUG)

(def uri "https://test.url")

(def login "login")
(def password "password")





```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
