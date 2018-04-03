(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
'use strict';
// For more information about browser field, check out the browser field at https://github.com/substack/browserify-handbook#browser-field.

var styleElementsInsertedAtTop = [];

var insertStyleElement = function(styleElement, options) {
    var head = document.head || document.getElementsByTagName('head')[0];
    var lastStyleElementInsertedAtTop = styleElementsInsertedAtTop[styleElementsInsertedAtTop.length - 1];

    options = options || {};
    options.insertAt = options.insertAt || 'bottom';

    if (options.insertAt === 'top') {
        if (!lastStyleElementInsertedAtTop) {
            head.insertBefore(styleElement, head.firstChild);
        } else if (lastStyleElementInsertedAtTop.nextSibling) {
            head.insertBefore(styleElement, lastStyleElementInsertedAtTop.nextSibling);
        } else {
            head.appendChild(styleElement);
        }
        styleElementsInsertedAtTop.push(styleElement);
    } else if (options.insertAt === 'bottom') {
        head.appendChild(styleElement);
    } else {
        throw new Error('Invalid value for parameter \'insertAt\'. Must be \'top\' or \'bottom\'.');
    }
};

module.exports = {
    // Create a <link> tag with optional data attributes
    createLink: function(href, attributes) {
        var head = document.head || document.getElementsByTagName('head')[0];
        var link = document.createElement('link');

        link.href = href;
        link.rel = 'stylesheet';

        for (var key in attributes) {
            if ( ! attributes.hasOwnProperty(key)) {
                continue;
            }
            var value = attributes[key];
            link.setAttribute('data-' + key, value);
        }

        head.appendChild(link);
    },
    // Create a <style> tag with optional data attributes
    createStyle: function(cssText, attributes, extraOptions) {
        extraOptions = extraOptions || {};

        var style = document.createElement('style');
        style.type = 'text/css';

        for (var key in attributes) {
            if ( ! attributes.hasOwnProperty(key)) {
                continue;
            }
            var value = attributes[key];
            style.setAttribute('data-' + key, value);
        }

        if (style.sheet) { // for jsdom and IE9+
            style.innerHTML = cssText;
            style.sheet.cssText = cssText;
            insertStyleElement(style, { insertAt: extraOptions.insertAt });
        } else if (style.styleSheet) { // for IE8 and below
            insertStyleElement(style, { insertAt: extraOptions.insertAt });
            style.styleSheet.cssText = cssText;
        } else { // for Chrome, Firefox, and Safari
            style.appendChild(document.createTextNode(cssText));
            insertStyleElement(style, { insertAt: extraOptions.insertAt });
        }
    }
};

},{}],2:[function(require,module,exports){
// Copyright Joyent, Inc. and other Node contributors.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the
// following conditions:
//
// The above copyright notice and this permission notice shall be included
// in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
// USE OR OTHER DEALINGS IN THE SOFTWARE.

'use strict';

// If obj.hasOwnProperty has been overridden, then calling
// obj.hasOwnProperty(prop) will break.
// See: https://github.com/joyent/node/issues/1707
function hasOwnProperty(obj, prop) {
  return Object.prototype.hasOwnProperty.call(obj, prop);
}

module.exports = function(qs, sep, eq, options) {
  sep = sep || '&';
  eq = eq || '=';
  var obj = {};

  if (typeof qs !== 'string' || qs.length === 0) {
    return obj;
  }

  var regexp = /\+/g;
  qs = qs.split(sep);

  var maxKeys = 1000;
  if (options && typeof options.maxKeys === 'number') {
    maxKeys = options.maxKeys;
  }

  var len = qs.length;
  // maxKeys <= 0 means that we should not limit keys count
  if (maxKeys > 0 && len > maxKeys) {
    len = maxKeys;
  }

  for (var i = 0; i < len; ++i) {
    var x = qs[i].replace(regexp, '%20'),
        idx = x.indexOf(eq),
        kstr, vstr, k, v;

    if (idx >= 0) {
      kstr = x.substr(0, idx);
      vstr = x.substr(idx + 1);
    } else {
      kstr = x;
      vstr = '';
    }

    k = decodeURIComponent(kstr);
    v = decodeURIComponent(vstr);

    if (!hasOwnProperty(obj, k)) {
      obj[k] = v;
    } else if (isArray(obj[k])) {
      obj[k].push(v);
    } else {
      obj[k] = [obj[k], v];
    }
  }

  return obj;
};

var isArray = Array.isArray || function (xs) {
  return Object.prototype.toString.call(xs) === '[object Array]';
};

},{}],3:[function(require,module,exports){
// Copyright Joyent, Inc. and other Node contributors.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the
// following conditions:
//
// The above copyright notice and this permission notice shall be included
// in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
// USE OR OTHER DEALINGS IN THE SOFTWARE.

'use strict';

var stringifyPrimitive = function(v) {
  switch (typeof v) {
    case 'string':
      return v;

    case 'boolean':
      return v ? 'true' : 'false';

    case 'number':
      return isFinite(v) ? v : '';

    default:
      return '';
  }
};

module.exports = function(obj, sep, eq, name) {
  sep = sep || '&';
  eq = eq || '=';
  if (obj === null) {
    obj = undefined;
  }

  if (typeof obj === 'object') {
    return map(objectKeys(obj), function(k) {
      var ks = encodeURIComponent(stringifyPrimitive(k)) + eq;
      if (isArray(obj[k])) {
        return map(obj[k], function(v) {
          return ks + encodeURIComponent(stringifyPrimitive(v));
        }).join(sep);
      } else {
        return ks + encodeURIComponent(stringifyPrimitive(obj[k]));
      }
    }).join(sep);

  }

  if (!name) return '';
  return encodeURIComponent(stringifyPrimitive(name)) + eq +
         encodeURIComponent(stringifyPrimitive(obj));
};

var isArray = Array.isArray || function (xs) {
  return Object.prototype.toString.call(xs) === '[object Array]';
};

function map (xs, f) {
  if (xs.map) return xs.map(f);
  var res = [];
  for (var i = 0; i < xs.length; i++) {
    res.push(f(xs[i], i));
  }
  return res;
}

var objectKeys = Object.keys || function (obj) {
  var res = [];
  for (var key in obj) {
    if (Object.prototype.hasOwnProperty.call(obj, key)) res.push(key);
  }
  return res;
};

},{}],4:[function(require,module,exports){
'use strict';

exports.decode = exports.parse = require('./decode');
exports.encode = exports.stringify = require('./encode');

},{"./decode":2,"./encode":3}],5:[function(require,module,exports){
var css = "@CHARSET \"utf-8\";\n/*---------------------------------------\n\tBase css\n---------------------------------------*/\nhtml,\nbody,\ndiv,\nspan,\np,\npre,\na,\ncite,\ncode,\nem,\nimg,\nsmall,\nstrong,\nsub,\nsup,\nu,\ni,\ncenter,\ndl,\ndt,\ndd,\nol,\nul,\nli,\nfieldset,\nform,\nlabel,\nlegend,\narticle,\ntable,\ncaption,\ntbody,\ntfoot,\nthead,\ntr,\nth,\ntd,\naside,\ncanvas,\ndetails,\nfigure,\nfigcaption,\nfooter,\nheader,\nmenu,\nnav,\nsection,\nsummary,\nmark {\n  margin: 0;\n  padding: 0;\n  font-family: 'NanumGothic';\n}\n@font-face {\n  font-family: 'NanumGothic';\n  src: url('/css/font/NanumGothic.eot');\n  src: url('/css/font/NanumGothic.eot?#iefix') format('embedded-opentype'),\n\t\t url('/css/font/NanumGothic.woff') format('woff'),\n\t\t url('/css/font/NanumGothic.ttf') format('truetype');\n  font-weight: normal;\n  font-style: normal;\n}\n.blind,\ncaption {\n  font-size: 0;\n  line-height: 0;\n  text-indent: -10000px;\n}\nul,\nli {\n  list-style: none;\n}\na:focus {\n  outline: none;\n}\na:link,\na:visited,\na:active {\n  font-family: 'NanumGothic', arial, gulim, dotum;\n  text-decoration: none;\n}\na:hover {\n  text-decoration: none;\n}\na img,\nfieldset {\n  border: 0;\n}\nbutton {\n  cursor: pointer;\n  border: 0;\n}\n"; (require("browserify-css").createStyle(css, { "href": "src/app/app.css" }, { "insertAt": "bottom" })); module.exports = css;
},{"browserify-css":1}],6:[function(require,module,exports){
var css = ".login-bg {\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: #191919 url(\"/css/images/login-bg.png\") no-repeat bottom right;\n}\n.login-logo {\n  position: absolute;\n  top: 50px;\n  left: 75px;\n}\n"; (require("browserify-css").createStyle(css, { "href": "src/app/login.css" }, { "insertAt": "bottom" })); module.exports = css;
},{"browserify-css":1}],7:[function(require,module,exports){
'use strict';

Object.defineProperty(exports, "__esModule", {
	value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _promisePolyfill = require('promise-polyfill');

var _promisePolyfill2 = _interopRequireDefault(_promisePolyfill);

require('matchmedia-polyfill');

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var _reactDom = require('react-dom');

var _reactDom2 = _interopRequireDefault(_reactDom);

var _login = require('../components/login/login.js');

var _login2 = _interopRequireDefault(_login);

require('./app.css');

require('./login.css');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

if (!window.Promise) {
	window.Promise = _promisePolyfill2.default;
}

var App = function (_React$Component) {
	_inherits(App, _React$Component);

	function App() {
		_classCallCheck(this, App);

		return _possibleConstructorReturn(this, (App.__proto__ || Object.getPrototypeOf(App)).apply(this, arguments));
	}

	_createClass(App, [{
		key: 'redirectToIndex',
		value: function redirectToIndex() {}
	}, {
		key: 'render',
		value: function render() {
			return _react2.default.createElement(
				'div',
				{ className: 'login-bg' },
				_react2.default.createElement(_login2.default, null)
			);
		}
	}]);

	return App;
}(_react2.default.Component);

exports.default = App;


_reactDom2.default.render(_react2.default.createElement(App, null), document.getElementById('root'));

},{"../components/login/login.js":9,"./app.css":5,"./login.css":6,"matchmedia-polyfill":"matchmedia-polyfill","promise-polyfill":"promise-polyfill","react":"react","react-dom":"react-dom"}],8:[function(require,module,exports){
var css = ".login-box {\n  position: relative;\n  top: 27.6%;\n  margin: auto;\n  width: 500px;\n  height: 360px;\n  background: #ffffff;\n}\n.login-title {\n  padding: 35px 0 25px;\n  font-size: 40px;\n  font-weight: bold;\n  color: #333333;\n  text-align: center;\n  border-bottom: 1px solid #d3d3d3;\n}\n.login-wrap {\n  margin: 50px auto 0;\n  width: 360px;\n}\n.login-wrap:after {\n  content: '';\n  display: block;\n  clear: both;\n}\n.login-container,\n.login-btn {\n  float: left;\n}\n.login-container input {\n  width: 200px;\n  height: 31px;\n  margin: 0px 2px 2px 0;\n  padding-left: 60px;\n  font-size: 13px;\n  color: #333333;\n  border: 1px solid #999999;\n  background: #ffffff;\n  -webkit-border-radius: 2px;\n  -moz-border-radius: 2px;\n  -ms-border-radius: 2px;\n  border-radius: 2px;\n}\n.login-container input.login-email {\n  background: url(\"/css/images/login-icon.png\") no-repeat 10px 0;\n}\n.login-container input.login-pw {\n  background: url(\"/css/images/login-icon.png\") no-repeat 10px -37px;\n}\n.login-btn {\n  width: 90px;\n  height: 72px;\n  color: #ffffff;\n  font-size: 20px;\n  font-weight: bold;\n  background: #e0002a;\n  border: 1px solid #9e0220;\n  -webkit-border-radius: 3px;\n  -moz-border-radius: 3px;\n  -ms-border-radius: 3px;\n  border-radius: 3px;\n}\n.save-chk {\n  width: 360px;\n  margin: 6px auto 20px;\n  font-size: 12px;\n  color: #666666;\n}\n.save-chk input[type=\"checkbox\"] {\n  vertical-align: middle;\n  margin-bottom: 6px;\n}\n.forget-pw {\n  margin: 6px 0 0 73px;\n  font-size: 12px;\n  color: #666666;\n}\n.forget-pw:after {\n  content: '';\n  display: inline-block;\n  position: relative;\n  top: 1px;\n  left: 6px;\n  width: 5px;\n  height: 9px;\n  background: url(\"/css/images/login-icon.png\") no-repeat 0 -91px;\n}\n"; (require("browserify-css").createStyle(css, { "href": "src/components/login/login.css" }, { "insertAt": "bottom" })); module.exports = css;
},{"browserify-css":1}],9:[function(require,module,exports){
'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

require('./login.css');

var _post = require('../../services/post');

var service = _interopRequireWildcard(_post);

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } else { var newObj = {}; if (obj != null) { for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key]; } } newObj.default = obj; return newObj; } }

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var LoginForm = function (_React$Component) {
    _inherits(LoginForm, _React$Component);

    function LoginForm(props) {
        _classCallCheck(this, LoginForm);

        var _this = _possibleConstructorReturn(this, (LoginForm.__proto__ || Object.getPrototypeOf(LoginForm)).call(this, props));

        _this.state = {
            email: "lpeterson2a@google.co.uk",
            password: "1111",
            profile: "",
            fetching: false // tells whether the request is waiting for response or not
        };
        _this.handleChange = _this.handleChange.bind(_this);
        _this.handleLogin = _this.handleLogin.bind(_this);
        return _this;
    }

    _createClass(LoginForm, [{
        key: 'handleChange',
        value: function handleChange(e) {
            var nextState = {};
            nextState[e.target.name] = e.target.value;
            //console.log('nestState', nextState);
            this.setState(nextState);
        }
    }, {
        key: 'handleLogin',
        value: function handleLogin(e) {
            var _this2 = this;

            //= async () => {
            console.log('Button was clicked!', e);
            var emailRegex = /(.+)@(.+){2,}\.(.+){2,}/;
            var emailValid = emailRegex.test(this.state.email);

            console.log(emailValid);
            if (!emailValid) {
                alert('이메일을 입력하세요.');
                return;
            }

            this.setState({
                fetching: true // requesting..
            });

            try {
                // // wait for two promises
                // const info = await Promise.all([
                //     service.getLogin(loginInfo);
                // ]);

                service.getLogin({
                    username: this.state.email,
                    password: this.state.password
                }).then(function (res) {
                    _this2.setState({
                        fetching: false
                    });
                    window.location.href = "/home";
                }).catch(function (res) {
                    //const data = res.data;
                    _this2.setState({
                        fetching: false
                    });
                    console.log('error occurred', res);
                });

                // console.log(info);
            } catch (e) {
                // if err, stop at this point
                this.setState({
                    fetching: false
                });
                //this.showWarning();
                console.log('error occurred', e);
            }
        }
    }, {
        key: 'render',
        value: function render() {
            return _react2.default.createElement(
                'div',
                { className: 'login-box' },
                _react2.default.createElement(
                    'form',
                    { action: '', method: '', name: 'loginForm' },
                    _react2.default.createElement(
                        'div',
                        { className: 'login-title' },
                        '\uB85C\uADF8\uC778'
                    ),
                    _react2.default.createElement(
                        'div',
                        { className: 'login-wrap' },
                        _react2.default.createElement(
                            'div',
                            { className: 'login-container' },
                            _react2.default.createElement(
                                'p',
                                null,
                                _react2.default.createElement('input', { name: 'email', type: 'text', className: 'login-email', onChange: this.handleChange, value: this.state.email, placeholder: '\uC774\uBA54\uC77C\uC8FC\uC18C' })
                            ),
                            _react2.default.createElement(
                                'p',
                                null,
                                _react2.default.createElement('input', { name: 'password', type: 'password', className: 'login-pw', onChange: this.handleChange, value: this.state.password, placeholder: '\uBE44\uBC00\uBC88\uD638' })
                            )
                        ),
                        _react2.default.createElement(
                            'button',
                            { onClick: this.handleLogin, type: 'button', className: 'login-btn' },
                            'LOGIN'
                        )
                    ),
                    _react2.default.createElement(
                        'div',
                        { className: 'save-chk' },
                        _react2.default.createElement('input', { type: 'checkbox', name: '', value: '' }),
                        '\uB85C\uADF8\uC778 \uC815\uBCF4 \uC800\uC7A5'
                    ),
                    _react2.default.createElement(
                        'a',
                        { href: '#', className: 'forget-pw' },
                        '\uC774\uBA54\uC77C \uB610\uB294 \uBE44\uBC00\uBC88\uD638\uB97C \uC78A\uC73C\uC168\uB098\uC694?'
                    )
                )
            );
        }
    }]);

    return LoginForm;
}(_react2.default.Component);

;

exports.default = LoginForm;

},{"../../services/post":11,"./login.css":8,"react":"react"}],10:[function(require,module,exports){
'use strict';

Object.defineProperty(exports, "__esModule", {
	value: true
});
var nullPath = '/';
var attrName = 'data-context';

var contextScriptElement = document.querySelector('script[' + attrName + ']');
var contextPath = (contextScriptElement && contextScriptElement.getAttribute(attrName) ? contextScriptElement.getAttribute(attrName) : nullPath) || nullPath;

exports.default = contextPath;

},{}],11:[function(require,module,exports){
'use strict';

Object.defineProperty(exports, "__esModule", {
	value: true
});
exports.getBodyPromotionData = getBodyPromotionData;
exports.getBodyCategoryData = getBodyCategoryData;
exports.getCategoryMovieList = getCategoryMovieList;
exports.getCategoryDetail = getCategoryDetail;
exports.getMovieDetailData = getMovieDetailData;
exports.getEpisodeData = getEpisodeData;
exports.getTrailersData = getTrailersData;
exports.getSimilarsData = getSimilarsData;
exports.getSearchResult = getSearchResult;
exports.getLogin = getLogin;
exports.logout = logout;
exports.getCurrentNotifications = getCurrentNotifications;
exports.getMenus = getMenus;
exports.getUser = getUser;

var _axios = require('axios');

var _axios2 = _interopRequireDefault(_axios);

var _context = require('./context.js');

var _context2 = _interopRequireDefault(_context);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function doGet(url) {
	for (var _len = arguments.length, param = Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
		param[_key - 1] = arguments[_key];
	}

	return applyErrorHandling(_axios2.default.get.apply(_axios2.default, [_context2.default + url].concat(param)));
}

function doPost(url) {
	for (var _len2 = arguments.length, param = Array(_len2 > 1 ? _len2 - 1 : 0), _key2 = 1; _key2 < _len2; _key2++) {
		param[_key2 - 1] = arguments[_key2];
	}

	return applyErrorHandling(_axios2.default.post.apply(_axios2.default, [_context2.default + url].concat(param)));
}

function applyErrorHandling(prom) {
	return prom.then(function (res) {
		return prom;
	}).catch(function (err) {
		console.error(err.response);
		// alert(err.response.data.message);
		return prom;
	});
}

function getBodyPromotionData() {
	return doGet('v1/promotions');
	// return axios.get('/services/local/body_promotion.json');
}

function getBodyCategoryData() {
	return doGet('v1/categories');
	// return axios.get('/services/local/body_category.json');
}

function getCategoryMovieList(categoryId) {
	return doGet('v1/contents?category=' + categoryId);
	// return axios.get('/services/local/body_category_movielist.json');
}

function getCategoryDetail(categoryId) {
	return doGet('v1/categories/' + categoryId);
	// 
}

function getMovieDetailData(movieId) {
	return doGet('v1/contents/' + movieId);
	// return axios.get('/services/local/body_moviedetail.json');
}

function getEpisodeData(movieId) {
	return doGet('v1/contents/' + movieId + '/episodes');
	// return axios.get('/services/local/episode.json');
}

function getTrailersData(movieId) {
	return doGet('v1/contents/' + movieId + '/trailers');
	// return axios.get('/services/local/trailers.json');
}

function getSimilarsData(movieId, category) {
	return doGet('v1/contents/' + movieId + '/similars?category=' + category);
	// return axios.get('/services/local/similars.json');
}

function getSearchResult(keyword) {
	return doGet('v1/contents/search?title=' + keyword);
	// return axios.get('/services/local/body_search_result.json');
}

function getLogin(loginInfo) {
	// return {
	//     email: "garfield@sk.com",
	//     pw: "1234",
	//     profile : "JIM"
	// }
	var querystring = require('querystring');
	return doPost('login', querystring.stringify(loginInfo));
};

function logout() {
	return doPost('logout');
}

function getCurrentNotifications() {
	// return axios.get('/services/local/notifications.json');
	return doGet('v1/notifications');
}

function getMenus() {
	// return axios.get('/services/local/menu.json')
	return doGet('v1/categories');
}

function getUser() {
	return doGet('v1/profiles');
	//return axios.get('/services/local/user.json');
}

},{"./context.js":10,"axios":"axios","querystring":4}]},{},[7]);
