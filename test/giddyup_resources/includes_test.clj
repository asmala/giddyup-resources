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

(deftest test-include-bootstrap-css
  (testing "include-bootstrap-css"
    (let [result (html (include-bootstrap-css))]
      (is (= result
             (html (include-css (res "/css/bootstrap.css"))))))
    (testing "minified"
      (let [result (html (include-bootstrap-css :minified? true))]
        (is (= result
               (html (include-css (res "/css/bootstrap.min.css")))))))
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
    (testing "without jquery"
      (let [result (html (include-bootstrap-js :jquery? false))]
        (is (= result
               (html (include-js (res "/js/bootstrap.js")))))))))

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