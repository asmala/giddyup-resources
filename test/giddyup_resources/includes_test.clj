(ns giddyup-resources.includes-test
  (:use [clojure.test]
        [giddyup-resources.includes]
        [giddyup-resources.test-support]
        [hiccup.core :only [html]]
        [hiccup.page :only [include-css include-js]]))

(defn- res
  "Returns the full resource path for `file-components`."
  [& file-components]
  (apply str "/giddyup" file-components))

(defn- cdn-res
  "Returns the full resource path for `file-components` hosted on CDNJS."
  [& file-components]
  (apply str "http://cdnjs.cloudflare.com/ajax/libs" file-components))

(deftest test-include-bootstrap-css
  (testing "include-bootstrap-css"
    (let [result (html (include-bootstrap-css))]
      (is (= result
             (html (include-css (res "/css/bootstrap.css")
                                (res "/css/bootstrap-responsive.css"))))))
    (testing "minified"
      (let [result (html (include-bootstrap-css {:minified? true
                                                 :responsive? false}))]
        (is (= result
               (html (include-css (res "/css/bootstrap.min.css")))))))
    (testing "cdn"
      (let [result (html (include-bootstrap-css {:location :cdn
                                                 :responsive? false}))]
        (is (= result
               (html (include-css
                      (cdn-res "/twitter-bootstrap/" (versions :bootstrap)
                               "/css/bootstrap.css")))))))))

(deftest test-include-bootstrap-js
  (testing "include-bootstrap-js"
    (let [result (html (include-bootstrap-js))]
      (is (= result
             (html (include-js (res "/js/jquery.js")
                               (res "/js/bootstrap.js"))))))
    (testing "minified"
      (let [result (html (include-bootstrap-js {:minified? true}))]
        (is (= result
               (html (include-js (res "/js/jquery.min.js")
                                 (res "/js/bootstrap.min.js")))))))
    (testing "cdn"
      (let [result (html (include-bootstrap-js {:location :cdn}))]
        (is (= result
               (html (include-js
                      (cdn-res "/jquery/" (versions :jquery) "/jquery.js")
                      (cdn-res "/twitter-bootstrap/" (versions :bootstrap)
                               "/bootstrap.js")))))))))

(deftest test-include-bootstrap
  (testing "include-bootstrap"
    (let [result (html (include-bootstrap))]
      (is (= result
             (html (include-css (res "/css/bootstrap.css")
                                (res "/css/bootstrap-responsive.css"))
                   (include-js (res "/js/jquery.js")
                               (res "/js/bootstrap.js"))))))
    (testing "with arguments"
      (let [result (html (include-bootstrap {:minified? true :responsive? false}))]
        (is (= result
               (html (include-css (res "/css/bootstrap.min.css"))
                     (include-js (res "/js/jquery.min.js")
                                 (res "/js/bootstrap.min.js")))))))))

