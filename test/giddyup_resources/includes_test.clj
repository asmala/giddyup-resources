(ns giddyup-resources.includes-test
  (:use [clojure.test]
        [giddyup-resources.includes]
        [giddyup-resources.test-support]
        [hiccup.core :only [html]]
        [hiccup.page :only [include-css include-js]]))

(defn- res
  "Returns the full resource path for `file`."
  [file]
  (str "/giddyup" file))

(defn- cdn-res
  "Returns the full resource path for `file` hosted on CDNJS."
  [file]
  (str "http://cdnjs.cloudflare.com/ajax/libs" file))

(deftest test-include-bootstrap-css
  (testing "include-bootstrap-css"
    (let [result (html (include-bootstrap-css))]
      (is (= result
             (html (include-css (res "/css/bootstrap.css"))))))
    (testing "minified"
      (let [result (html (include-bootstrap-css :minified? true))]
        (is (= result
               (html (include-css (res "/css/bootstrap.min.css")))))))
    (testing "cdn"
      (let [result (html (include-bootstrap-css :cdn? true))]
        (is (= result
               (html (include-css
                      (cdn-res "/twitter-bootstrap/2.1.1/css/bootstrap.css")))))))
    (testing "responsive"
      (let [result (html (include-bootstrap-css :responsive? true))]
        (is (= result
               (html (include-css (res "/css/bootstrap.css")
                                  (res "/css/bootstrap-responsive.css")))))))))

(deftest test-include-bootstrap-js
  (testing "include-bootstrap-js"
    (let [result (html (include-bootstrap-js))]
      (is (= result
             (html (include-js (res "/js/jquery.js")
                               (res "/js/bootstrap.js"))))))
    (testing "minified"
      (let [result (html (include-bootstrap-js :minified? true))]
        (is (= result
               (html (include-js (res "/js/jquery.min.js")
                                 (res "/js/bootstrap.min.js")))))))
    (testing "cdn"
      (let [result (html (include-bootstrap-js :cdn? true))]
        (is (= result
               (html (include-js
                      (cdn-res "/jquery/1.8.2/jquery.js")
                      (cdn-res "/twitter-bootstrap/2.1.1/bootstrap.js")))))))))

(deftest test-include-bootstrap
  (testing "include-bootstrap"
    (let [result (html (include-bootstrap))]
      (is (= result
             (html (include-css (res "/css/bootstrap.css"))
                   (include-js (res "/js/jquery.js")
                               (res "/js/bootstrap.js"))))))
    (testing "with arguments"
      (let [result (html (include-bootstrap :minified? true))]
        (is (= result
               (html (include-css (res "/css/bootstrap.min.css"))
                     (include-js (res "/js/jquery.min.js")
                                 (res "/js/bootstrap.min.js")))))))))

