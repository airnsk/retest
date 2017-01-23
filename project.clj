(defproject re2 "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [cljs-react-material-ui "0.2.30"]
                 [reagent "0.6.0" :exclusions [cljsjs/react]]
                 [reagent-utils "0.2.0"]
                 [re-frame "0.9.1"]
                 [cljs-ajax "0.5.8"]
                 [day8.re-frame/http-fx "0.1.3"]
                 [secretary "1.2.3"]
                 [venantius/accountant "0.1.6"
                  :exclusions [org.clojure/tools.reader]]
                 ]

  :plugins [[lein-cljsbuild "1.1.4"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]
            :nrepl-port 7777
            :ring-handler re2.core/handler
        }





  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.8.2"]

                    [re-frisk "0.3.2"]
                    [com.cemerick/piggieback "0.2.1"]
                    [figwheel-sidecar "0.5.8"]
                    [proto-repl "0.3.1"]]

    :plugins      [[lein-figwheel "0.5.8"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "re2.core/mount-root"}
     :compiler     {:main                 re2.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload
                                          ]
                    :parallel-build true
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            re2.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}


    ]}

  )
