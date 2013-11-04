goog.addDependency("base.js", ['goog'], []);
goog.addDependency("../cljs/core.js", ['cljs.core'], ['goog.array', 'goog.array', 'goog.object', 'goog.object', 'goog.string.StringBuffer', 'goog.string.StringBuffer', 'goog.string', 'goog.string']);
goog.addDependency("../simclient/transform.js", ['simclient.transform'], ['cljs.core']);
goog.addDependency("../simclient/landmark.js", ['simclient.landmark'], ['cljs.core']);
goog.addDependency("../simclient/dom.js", ['simclient.dom'], ['cljs.core']);
goog.addDependency("../clojure/string.js", ['clojure.string'], ['cljs.core', 'goog.string', 'goog.string.StringBuffer']);
goog.addDependency("../simclient/canvas.js", ['simclient.canvas'], ['cljs.core', 'simclient.landmark', 'simclient.dom', 'simclient.transform', 'clojure.string']);
goog.addDependency("../simclient/robot.js", ['simclient.robot'], ['cljs.core']);
goog.addDependency("../simclient/core.js", ['simclient.core'], ['cljs.core', 'simclient.robot', 'simclient.canvas']);