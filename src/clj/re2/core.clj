(ns re2.core)


(defn handler [request]
  {:status 200
   :body (slurp "resources/public/index.html")})
