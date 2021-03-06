/**
 * Copyright (c) Tiny Technologies, Inc. All rights reserved.
 * Licensed under the LGPL or a commercial license.
 * For LGPL see License.txt in the project root for license information.
 * For commercial licenses see https://www.tiny.cloud/
 *
 * Version: 5.6.1 (2020-11-25)
 */
!function () {
    "use strict";
    var e, n, t, r = tinymce.util.Tools.resolve("tinymce.PluginManager"), s = function (e) {
        return function () {
            return e
        }
    }, i = s(!1), o = s(!0), a = function () {
        return l
    }, l = (e = function (e) {
        return e.isNone()
    }, {
        fold: function (e, n) {
            return e()
        }, is: i, isSome: i, isNone: o, getOr: t = function (e) {
            return e
        }, getOrThunk: n = function (e) {
            return e()
        }, getOrDie: function (e) {
            throw new Error(e || "error: getOrDie called on none.")
        }, getOrNull: s(null), getOrUndefined: s(undefined), or: t, orThunk: n, map: a, each: function () {
        }, bind: a, exists: i, forall: o, filter: a, equals: e, equals_: e, toArray: function () {
            return []
        }, toString: s("none()")
    }), u = function (t) {
        var e = s(t), n = function () {
            return a
        }, r = function (e) {
            return e(t)
        }, a = {
            fold: function (e, n) {
                return n(t)
            },
            is: function (e) {
                return t === e
            },
            isSome: o,
            isNone: i,
            getOr: e,
            getOrThunk: e,
            getOrDie: e,
            getOrNull: e,
            getOrUndefined: e,
            or: n,
            orThunk: n,
            map: function (e) {
                return u(e(t))
            },
            each: function (e) {
                e(t)
            },
            bind: r,
            exists: r,
            forall: r,
            filter: function (e) {
                return e(t) ? a : l
            },
            toArray: function () {
                return [t]
            },
            toString: function () {
                return "some(" + t + ")"
            },
            equals: function (e) {
                return e.is(t)
            },
            equals_: function (e, n) {
                return e.fold(i, function (e) {
                    return n(t, e)
                })
            }
        };
        return a
    }, c = {
        some: u, none: a, from: function (e) {
            return null === e || e === undefined ? l : u(e)
        }
    }, p = function (e) {
        return n = e, (t = 0) <= t && t < n.length ? c.some(n[t]) : c.none();
        var n, t
    }, d = tinymce.util.Tools.resolve("tinymce.dom.DOMUtils");

    function g(e) {
        return e && "PRE" === e.nodeName && -1 !== e.className.indexOf("language-")
    }

    function m(t) {
        return function (e, n) {
            return t(n)
        }
    }

    var f = "undefined" != typeof window ? window : Function("return this;")(), h = {}, b = {exports: h}, y = {};
    !function (n, t, r, p) {
        var e = window.Prism;
        window.Prism = {manual: !0}, function (e) {
            "object" == typeof t && void 0 !== r ? r.exports = e() : "function" == typeof n && n.amd ? n([], e) : ("undefined" != typeof window ? window : void 0 !== y ? y : "undefined" != typeof self ? self : this).EphoxContactWrapper = e()
        }(function () {
            return function c(s, i, o) {
                function l(n, e) {
                    if (!i[n]) {
                        if (!s[n]) {
                            var t = "function" == typeof p && p;
                            if (!e && t) return t(n, !0);
                            if (u) return u(n, !0);
                            var r = new Error("Cannot find module '" + n + "'");
                            throw r.code = "MODULE_NOT_FOUND", r
                        }
                        var a = i[n] = {exports: {}};
                        s[n][0].call(a.exports, function (e) {
                            return l(s[n][1][e] || e)
                        }, a, a.exports, c, s, i, o)
                    }
                    return i[n].exports
                }

                for (var u = "function" == typeof p && p, e = 0; e < o.length; e++) l(o[e]);
                return l
            }({
                1: [function (e, n, t) {
                    Prism.languages.c = Prism.languages.extend("clike", {
                        comment: {
                            pattern: /\/\/(?:[^\r\n\\]|\\(?:\r\n?|\n|(?![\r\n])))*|\/\*[\s\S]*?(?:\*\/|$)/,
                            greedy: !0
                        },
                        "class-name": {
                            pattern: /(\b(?:enum|struct)\s+(?:__attribute__\s*\(\([\s\S]*?\)\)\s*)?)\w+/,
                            lookbehind: !0
                        },
                        keyword: /\b(?:__attribute__|_Alignas|_Alignof|_Atomic|_Bool|_Complex|_Generic|_Imaginary|_Noreturn|_Static_assert|_Thread_local|asm|typeof|inline|auto|break|case|char|const|continue|default|do|double|else|enum|extern|float|for|goto|if|int|long|register|return|short|signed|sizeof|static|struct|switch|typedef|union|unsigned|void|volatile|while)\b/,
                        "function": /[a-z_]\w*(?=\s*\()/i,
                        operator: />>=?|<<=?|->|([-+&|:])\1|[?:~]|[-+*/%&|^!=<>]=?/,
                        number: /(?:\b0x(?:[\da-f]+\.?[\da-f]*|\.[\da-f]+)(?:p[+-]?\d+)?|(?:\b\d+\.?\d*|\B\.\d+)(?:e[+-]?\d+)?)[ful]*/i
                    }), Prism.languages.insertBefore("c", "string", {
                        macro: {
                            pattern: /(^\s*)#\s*[a-z]+(?:[^\r\n\\/]|\/(?!\*)|\/\*(?:[^*]|\*(?!\/))*\*\/|\\(?:\r\n|[\s\S]))*/im,
                            lookbehind: !0,
                            greedy: !0,
                            alias: "property",
                            inside: {
                                string: [{
                                    pattern: /^(#\s*include\s*)<[^>]+>/,
                                    lookbehind: !0
                                }, Prism.languages.c.string],
                                comment: Prism.languages.c.comment,
                                directive: {pattern: /^(#\s*)[a-z]+/, lookbehind: !0, alias: "keyword"},
                                "directive-hash": /^#/,
                                punctuation: /##|\\(?=[\r\n])/,
                                expression: {pattern: /\S[\s\S]*/, inside: Prism.languages.c}
                            }
                        },
                        constant: /\b(?:__FILE__|__LINE__|__DATE__|__TIME__|__TIMESTAMP__|__func__|EOF|NULL|SEEK_CUR|SEEK_END|SEEK_SET|stdin|stdout|stderr)\b/
                    }), delete Prism.languages.c["boolean"]
                }, {}], 2: [function (e, n, t) {
                    Prism.languages.clike = {
                        comment: [{
                            pattern: /(^|[^\\])\/\*[\s\S]*?(?:\*\/|$)/,
                            lookbehind: !0
                        }, {pattern: /(^|[^\\:])\/\/.*/, lookbehind: !0, greedy: !0}],
                        string: {pattern: /(["'])(?:\\(?:\r\n|[\s\S])|(?!\1)[^\\\r\n])*\1/, greedy: !0},
                        "class-name": {
                            pattern: /(\b(?:class|interface|extends|implements|trait|instanceof|new)\s+|\bcatch\s+\()[\w.\\]+/i,
                            lookbehind: !0,
                            inside: {punctuation: /[.\\]/}
                        },
                        keyword: /\b(?:if|else|while|do|for|return|in|instanceof|function|new|try|throw|catch|finally|null|break|continue)\b/,
                        "boolean": /\b(?:true|false)\b/,
                        "function": /\w+(?=\()/,
                        number: /\b0x[\da-f]+\b|(?:\b\d+\.?\d*|\B\.\d+)(?:e[+-]?\d+)?/i,
                        operator: /[<>]=?|[!=]=?=?|--?|\+\+?|&&?|\|\|?|[?*/~^%]/,
                        punctuation: /[{}[\];(),.:]/
                    }
                }, {}], 3: [function (e, t, n) {
                    (function (n) {
                        (function () {
                            var e = function (u) {
                                var c = /\blang(?:uage)?-([\w-]+)\b/i, n = 0, O = {
                                    manual: u.Prism && u.Prism.manual,
                                    disableWorkerMessageHandler: u.Prism && u.Prism.disableWorkerMessageHandler,
                                    util: {
                                        encode: function a(e) {
                                            return e instanceof N ? new N(e.type, a(e.content), e.alias) : Array.isArray(e) ? e.map(a) : e.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/\u00a0/g, " ")
                                        }, type: function (e) {
                                            return Object.prototype.toString.call(e).slice(8, -1)
                                        }, objId: function (e) {
                                            return e.__id || Object.defineProperty(e, "__id", {value: ++n}), e.__id
                                        }, clone: function i(e, t) {
                                            var r, n;
                                            switch (t = t || {}, O.util.type(e)) {
                                                case"Object":
                                                    if (n = O.util.objId(e), t[n]) return t[n];
                                                    for (var a in r = {}, t[n] = r, e) e.hasOwnProperty(a) && (r[a] = i(e[a], t));
                                                    return r;
                                                case"Array":
                                                    return (n = O.util.objId(e), t[n]) ? t[n] : (r = [], t[n] = r, e.forEach(function (e, n) {
                                                        r[n] = i(e, t)
                                                    }), r);
                                                default:
                                                    return e
                                            }
                                        }, getLanguage: function (e) {
                                            for (; e && !c.test(e.className);) e = e.parentElement;
                                            return e ? (e.className.match(c) || [, "none"])[1].toLowerCase() : "none"
                                        }, currentScript: function () {
                                            if ("undefined" == typeof document) return null;
                                            if ("currentScript" in document) return document.currentScript;
                                            try {
                                                throw new Error
                                            } catch (r) {
                                                var e = (/at [^(\r\n]*\((.*):.+:.+\)$/i.exec(r.stack) || [])[1];
                                                if (e) {
                                                    var n = document.getElementsByTagName("script");
                                                    for (var t in n) if (n[t].src == e) return n[t]
                                                }
                                                return null
                                            }
                                        }, isActive: function (e, n, t) {
                                            for (var r = "no-" + n; e;) {
                                                var a = e.classList;
                                                if (a.contains(n)) return !0;
                                                if (a.contains(r)) return !1;
                                                e = e.parentElement
                                            }
                                            return !!t
                                        }
                                    },
                                    languages: {
                                        extend: function (e, n) {
                                            var t = O.util.clone(O.languages[e]);
                                            for (var r in n) t[r] = n[r];
                                            return t
                                        }, insertBefore: function (t, e, n, r) {
                                            var a = (r = r || O.languages)[t], s = {};
                                            for (var i in a) if (a.hasOwnProperty(i)) {
                                                if (i == e) for (var o in n) n.hasOwnProperty(o) && (s[o] = n[o]);
                                                n.hasOwnProperty(i) || (s[i] = a[i])
                                            }
                                            var l = r[t];
                                            return r[t] = s, O.languages.DFS(O.languages, function (e, n) {
                                                n === l && e != t && (this[e] = s)
                                            }), s
                                        }, DFS: function l(e, n, t, r) {
                                            r = r || {};
                                            var a, s, i = O.util.objId;
                                            for (var o in e) {
                                                e.hasOwnProperty(o) && (n.call(e, o, e[o], t || o), a = e[o], "Object" !== (s = O.util.type(a)) || r[i(a)] ? "Array" !== s || r[i(a)] || (r[i(a)] = !0, l(a, n, o, r)) : (r[i(a)] = !0, l(a, n, null, r)))
                                            }
                                        }
                                    },
                                    plugins: {},
                                    highlightAll: function (e, n) {
                                        O.highlightAllUnder(document, e, n)
                                    },
                                    highlightAllUnder: function (e, n, t) {
                                        var r = {
                                            callback: t,
                                            container: e,
                                            selector: 'code[class*="language-"], [class*="language-"] code, code[class*="lang-"], [class*="lang-"] code'
                                        };
                                        O.hooks.run("before-highlightall", r), r.elements = Array.prototype.slice.apply(r.container.querySelectorAll(r.selector)), O.hooks.run("before-all-elements-highlight", r);
                                        for (var a, s = 0; a = r.elements[s++];) O.highlightElement(a, !0 === n, r.callback)
                                    },
                                    highlightElement: function (e, n, t) {
                                        var r = O.util.getLanguage(e), a = O.languages[r];
                                        e.className = e.className.replace(c, "").replace(/\s+/g, " ") + " language-" + r;
                                        var s = e.parentElement;
                                        s && "pre" === s.nodeName.toLowerCase() && (s.className = s.className.replace(c, "").replace(/\s+/g, " ") + " language-" + r);
                                        var i, o = {element: e, language: r, grammar: a, code: e.textContent};

                                        function l(e) {
                                            o.highlightedCode = e, O.hooks.run("before-insert", o), o.element.innerHTML = o.highlightedCode, O.hooks.run("after-highlight", o), O.hooks.run("complete", o), t && t.call(o.element)
                                        }

                                        if (O.hooks.run("before-sanity-check", o), !o.code) return O.hooks.run("complete", o), void (t && t.call(o.element));
                                        O.hooks.run("before-highlight", o), o.grammar ? n && u.Worker ? ((i = new Worker(O.filename)).onmessage = function (e) {
                                            l(e.data)
                                        }, i.postMessage(JSON.stringify({
                                            language: o.language,
                                            code: o.code,
                                            immediateClose: !0
                                        }))) : l(O.highlight(o.code, o.grammar, o.language)) : l(O.util.encode(o.code))
                                    },
                                    highlight: function (e, n, t) {
                                        var r = {code: e, grammar: n, language: t};
                                        return O.hooks.run("before-tokenize", r), r.tokens = O.tokenize(r.code, r.grammar), O.hooks.run("after-tokenize", r), N.stringify(O.util.encode(r.tokens), r.language)
                                    },
                                    tokenize: function (e, n) {
                                        var t = n.rest;
                                        if (t) {
                                            for (var r in t) n[r] = t[r];
                                            delete n.rest
                                        }
                                        var a = new s;
                                        return B(a, a.head, e), function C(e, n, t, r, a, s) {
                                            for (var i in t) if (t.hasOwnProperty(i) && t[i]) {
                                                var o = t[i];
                                                o = Array.isArray(o) ? o : [o];
                                                for (var l = 0; l < o.length; ++l) {
                                                    if (s && s.cause == i + "," + l) return;
                                                    var u, c = o[l], p = c.inside, d = !!c.lookbehind, g = !!c.greedy,
                                                        m = 0, f = c.alias;
                                                    g && !c.pattern.global && (u = c.pattern.toString().match(/[imsuy]*$/)[0], c.pattern = RegExp(c.pattern.source, u + "g"));
                                                    for (var h = c.pattern || c, b = r.next, y = a; b !== n.tail && !(s && y >= s.reach); y += b.value.length, b = b.next) {
                                                        var v = b.value;
                                                        if (n.length > e.length) return;
                                                        if (!(v instanceof N)) {
                                                            var w, k, x, _, P, F, A = 1;
                                                            if (g && b != n.tail.prev) {
                                                                h.lastIndex = y;
                                                                var S = h.exec(e);
                                                                if (!S) break;
                                                                var $ = S.index + (d && S[1] ? S[1].length : 0),
                                                                    j = S.index + S[0].length, E = y;
                                                                for (E += b.value.length; E <= $;) b = b.next, E += b.value.length;
                                                                if (E -= b.value.length, y = E, b.value instanceof N) continue;
                                                                for (var z = b; z !== n.tail && (E < j || "string" == typeof z.value); z = z.next) A++, E += z.value.length;
                                                                A--, v = e.slice(y, E), S.index -= y
                                                            } else {
                                                                h.lastIndex = 0;
                                                                var S = h.exec(v)
                                                            }
                                                            S && (d && (m = S[1] ? S[1].length : 0), $ = S.index + m, w = S[0].slice(m), j = $ + w.length, k = v.slice(0, $), x = v.slice(j), _ = y + v.length, s && _ > s.reach && (s.reach = _), P = b.prev, k && (P = B(n, P, k), y += k.length), T(n, P, A), F = new N(i, p ? O.tokenize(w, p) : w, f, w), b = B(n, P, F), x && B(n, b, x), 1 < A && C(e, n, t, b.prev, y, {
                                                                cause: i + "," + l,
                                                                reach: _
                                                            }))
                                                        }
                                                    }
                                                }
                                            }
                                        }(e, a, n, a.head, 0), function (e) {
                                            var n = [], t = e.head.next;
                                            for (; t !== e.tail;) n.push(t.value), t = t.next;
                                            return n
                                        }(a)
                                    },
                                    hooks: {
                                        all: {}, add: function (e, n) {
                                            var t = O.hooks.all;
                                            t[e] = t[e] || [], t[e].push(n)
                                        }, run: function (e, n) {
                                            var t = O.hooks.all[e];
                                            if (t && t.length) for (var r, a = 0; r = t[a++];) r(n)
                                        }
                                    },
                                    Token: N
                                };

                                function N(e, n, t, r) {
                                    this.type = e, this.content = n, this.alias = t, this.length = 0 | (r || "").length
                                }

                                function s() {
                                    var e = {value: null, prev: null, next: null},
                                        n = {value: null, prev: e, next: null};
                                    e.next = n, this.head = e, this.tail = n, this.length = 0
                                }

                                function B(e, n, t) {
                                    var r = n.next, a = {value: t, prev: n, next: r};
                                    return n.next = a, r.prev = a, e.length++, a
                                }

                                function T(e, n, t) {
                                    for (var r = n.next, a = 0; a < t && r !== e.tail; a++) r = r.next;
                                    (n.next = r).prev = n, e.length -= a
                                }

                                if (u.Prism = O, N.stringify = function o(e, n) {
                                    if ("string" == typeof e) return e;
                                    if (Array.isArray(e)) {
                                        var t = "";
                                        return e.forEach(function (e) {
                                            t += o(e, n)
                                        }), t
                                    }
                                    var r = {
                                        type: e.type,
                                        content: o(e.content, n),
                                        tag: "span",
                                        classes: ["token", e.type],
                                        attributes: {},
                                        language: n
                                    }, a = e.alias;
                                    a && (Array.isArray(a) ? Array.prototype.push.apply(r.classes, a) : r.classes.push(a)), O.hooks.run("wrap", r);
                                    var s = "";
                                    for (var i in r.attributes) s += " " + i + '="' + (r.attributes[i] || "").replace(/"/g, "&quot;") + '"';
                                    return "<" + r.tag + ' class="' + r.classes.join(" ") + '"' + s + ">" + r.content + "</" + r.tag + ">"
                                }, !u.document) return u.addEventListener && (O.disableWorkerMessageHandler || u.addEventListener("message", function (e) {
                                    var n = JSON.parse(e.data), t = n.language, r = n.code, a = n.immediateClose;
                                    u.postMessage(O.highlight(r, O.languages[t], t)), a && u.close()
                                }, !1)), O;
                                var e, t = O.util.currentScript();

                                function r() {
                                    O.manual || O.highlightAll()
                                }

                                return t && (O.filename = t.src, t.hasAttribute("data-manual") && (O.manual = !0)), O.manual || ("loading" === (e = document.readyState) || "interactive" === e && t && t.defer ? document.addEventListener("DOMContentLoaded", r) : window.requestAnimationFrame ? window.requestAnimationFrame(r) : window.setTimeout(r, 16)), O
                            }("undefined" != typeof window ? window : "undefined" != typeof WorkerGlobalScope && self instanceof WorkerGlobalScope ? self : {});
                            void 0 !== t && t.exports && (t.exports = e), void 0 !== n && (n.Prism = e)
                        }).call(this)
                    }).call(this, void 0 !== y ? y : "undefined" != typeof self ? self : "undefined" != typeof window ? window : {})
                }, {}], 4: [function (e, n, t) {
                    var r, a;
                    r = Prism, a = /\b(?:alignas|alignof|asm|auto|bool|break|case|catch|char|char8_t|char16_t|char32_t|class|compl|concept|const|consteval|constexpr|constinit|const_cast|continue|co_await|co_return|co_yield|decltype|default|delete|do|double|dynamic_cast|else|enum|explicit|export|extern|float|for|friend|goto|if|inline|int|int8_t|int16_t|int32_t|int64_t|uint8_t|uint16_t|uint32_t|uint64_t|long|mutable|namespace|new|noexcept|nullptr|operator|private|protected|public|register|reinterpret_cast|requires|return|short|signed|sizeof|static|static_assert|static_cast|struct|switch|template|this|thread_local|throw|try|typedef|typeid|typename|union|unsigned|using|virtual|void|volatile|wchar_t|while)\b/, r.languages.cpp = r.languages.extend("c", {
                        "class-name": [{
                            pattern: RegExp(/(\b(?:class|concept|enum|struct|typename)\s+)(?!<keyword>)\w+/.source.replace(/<keyword>/g, function () {
                                return a.source
                            })), lookbehind: !0
                        }, /\b[A-Z]\w*(?=\s*::\s*\w+\s*\()/, /\b[A-Z_]\w*(?=\s*::\s*~\w+\s*\()/i, /\w+(?=\s*<(?:[^<>]|<(?:[^<>]|<[^<>]*>)*>)*>\s*::\s*\w+\s*\()/],
                        keyword: a,
                        number: {
                            pattern: /(?:\b0b[01']+|\b0x(?:[\da-f']+\.?[\da-f']*|\.[\da-f']+)(?:p[+-]?[\d']+)?|(?:\b[\d']+\.?[\d']*|\B\.[\d']+)(?:e[+-]?[\d']+)?)[ful]*/i,
                            greedy: !0
                        },
                        operator: />>=?|<<=?|->|([-+&|:])\1|[?:~]|<=>|[-+*/%&|^!=<>]=?|\b(?:and|and_eq|bitand|bitor|not|not_eq|or|or_eq|xor|xor_eq)\b/,
                        "boolean": /\b(?:true|false)\b/
                    }), r.languages.insertBefore("cpp", "string", {
                        "raw-string": {
                            pattern: /R"([^()\\ ]{0,16})\([\s\S]*?\)\1"/,
                            alias: "string",
                            greedy: !0
                        }
                    }), r.languages.insertBefore("cpp", "class-name", {
                        "base-clause": {
                            pattern: /(\b(?:class|struct)\s+\w+\s*:\s*)(?:[^;{}"'])+?(?=\s*[;{])/,
                            lookbehind: !0,
                            greedy: !0,
                            inside: r.languages.extend("cpp", {})
                        }
                    }), r.languages.insertBefore("inside", "operator", {"class-name": /\b[a-z_]\w*\b(?!\s*::)/i}, r.languages.cpp["base-clause"])
                }, {}], 5: [function (e, n, t) {
                    !function (t) {
                        function r(e, t) {
                            return e.replace(/<<(\d+)>>/g, function (e, n) {
                                return "(?:" + t[+n] + ")"
                            })
                        }

                        function a(e, n, t) {
                            return RegExp(r(e, n), t || "")
                        }

                        function e(e, n) {
                            for (var t = 0; t < n; t++) e = e.replace(/<<self>>/g, function () {
                                return "(?:" + e + ")"
                            });
                            return e.replace(/<<self>>/g, "[^\\s\\S]")
                        }

                        var n = "bool byte char decimal double dynamic float int long object sbyte short string uint ulong ushort var void",
                            s = "class enum interface struct",
                            i = "add alias and ascending async await by descending from get global group into join let nameof not notnull on or orderby partial remove select set unmanaged value when where where",
                            o = "abstract as base break case catch checked const continue default delegate do else event explicit extern finally fixed for foreach goto if implicit in internal is lock namespace new null operator out override params private protected public readonly ref return sealed sizeof stackalloc static switch this throw try typeof unchecked unsafe using virtual volatile while yield";

                        function l(e) {
                            return "\\b(?:" + e.trim().replace(/ /g, "|") + ")\\b"
                        }

                        var u = l(s), c = RegExp(l(n + " " + s + " " + i + " " + o)), p = l(s + " " + i + " " + o),
                            d = l(n + " " + s + " " + o), g = e(/<(?:[^<>;=+\-*/%&|^]|<<self>>)*>/.source, 2),
                            m = e(/\((?:[^()]|<<self>>)*\)/.source, 2), f = /@?\b[A-Za-z_]\w*\b/.source,
                            h = r(/<<0>>(?:\s*<<1>>)?/.source, [f, g]),
                            b = r(/(?!<<0>>)<<1>>(?:\s*\.\s*<<1>>)*/.source, [p, h]), y = /\[\s*(?:,\s*)*\]/.source,
                            v = r(/<<0>>(?:\s*(?:\?\s*)?<<1>>)*(?:\s*\?)?/.source, [b, y]),
                            w = r(/[^,()<>[\];=+\-*/%&|^]|<<0>>|<<1>>|<<2>>/.source, [g, m, y]),
                            k = r(/\(<<0>>+(?:,<<0>>+)+\)/.source, [w]),
                            x = r(/(?:<<0>>|<<1>>)(?:\s*(?:\?\s*)?<<2>>)*(?:\s*\?)?/.source, [k, b, y]),
                            _ = {keyword: c, punctuation: /[<>()?,.:[\]]/},
                            P = /'(?:[^\r\n'\\]|\\.|\\[Uux][\da-fA-F]{1,8})'/.source,
                            F = /"(?:\\.|[^\\"\r\n])*"/.source, A = /@"(?:""|\\[\s\S]|[^\\"])*"(?!")/.source;
                        t.languages.csharp = t.languages.extend("clike", {
                            string: [{
                                pattern: a(/(^|[^$\\])<<0>>/.source, [A]),
                                lookbehind: !0,
                                greedy: !0
                            }, {
                                pattern: a(/(^|[^@$\\])<<0>>/.source, [F]),
                                lookbehind: !0,
                                greedy: !0
                            }, {pattern: RegExp(P), greedy: !0, alias: "character"}],
                            "class-name": [{
                                pattern: a(/(\busing\s+static\s+)<<0>>(?=\s*;)/.source, [b]),
                                lookbehind: !0,
                                inside: _
                            }, {
                                pattern: a(/(\busing\s+<<0>>\s*=\s*)<<1>>(?=\s*;)/.source, [f, x]),
                                lookbehind: !0,
                                inside: _
                            }, {
                                pattern: a(/(\busing\s+)<<0>>(?=\s*=)/.source, [f]),
                                lookbehind: !0
                            }, {
                                pattern: a(/(\b<<0>>\s+)<<1>>/.source, [u, h]),
                                lookbehind: !0,
                                inside: _
                            }, {
                                pattern: a(/(\bcatch\s*\(\s*)<<0>>/.source, [b]),
                                lookbehind: !0,
                                inside: _
                            }, {
                                pattern: a(/(\bwhere\s+)<<0>>/.source, [f]),
                                lookbehind: !0
                            }, {
                                pattern: a(/(\b(?:is(?:\s+not)?|as)\s+)<<0>>/.source, [v]),
                                lookbehind: !0,
                                inside: _
                            }, {
                                pattern: a(/\b<<0>>(?=\s+(?!<<1>>)<<2>>(?:\s*[=,;:{)\]]|\s+(?:in|when)\b))/.source, [x, d, f]),
                                inside: _
                            }],
                            keyword: c,
                            number: /(?:\b0(?:x[\da-f_]*[\da-f]|b[01_]*[01])|(?:\B\.\d+(?:_+\d+)*|\b\d+(?:_+\d+)*(?:\.\d+(?:_+\d+)*)?)(?:e[-+]?\d+(?:_+\d+)*)?)(?:ul|lu|[dflmu])?\b/i,
                            operator: />>=?|<<=?|[-=]>|([-+&|])\1|~|\?\?=?|[-+*/%&|^!=<>]=?/,
                            punctuation: /\?\.?|::|[{}[\];(),.:]/
                        }), t.languages.insertBefore("csharp", "number", {
                            range: {
                                pattern: /\.\./,
                                alias: "operator"
                            }
                        }), t.languages.insertBefore("csharp", "punctuation", {
                            "named-parameter": {
                                pattern: a(/([(,]\s*)<<0>>(?=\s*:)/.source, [f]),
                                lookbehind: !0,
                                alias: "punctuation"
                            }
                        }), t.languages.insertBefore("csharp", "class-name", {
                            namespace: {
                                pattern: a(/(\b(?:namespace|using)\s+)<<0>>(?:\s*\.\s*<<0>>)*(?=\s*[;{])/.source, [f]),
                                lookbehind: !0,
                                inside: {punctuation: /\./}
                            },
                            "type-expression": {
                                pattern: a(/(\b(?:default|typeof|sizeof)\s*\(\s*)(?:[^()\s]|\s(?!\s*\))|<<0>>)*(?=\s*\))/.source, [m]),
                                lookbehind: !0,
                                alias: "class-name",
                                inside: _
                            },
                            "return-type": {
                                pattern: a(/<<0>>(?=\s+(?:<<1>>\s*(?:=>|[({]|\.\s*this\s*\[)|this\s*\[))/.source, [x, b]),
                                inside: _,
                                alias: "class-name"
                            },
                            "constructor-invocation": {
                                pattern: a(/(\bnew\s+)<<0>>(?=\s*[[({])/.source, [x]),
                                lookbehind: !0,
                                inside: _,
                                alias: "class-name"
                            },
                            "generic-method": {
                                pattern: a(/<<0>>\s*<<1>>(?=\s*\()/.source, [f, g]),
                                inside: {
                                    "function": a(/^<<0>>/.source, [f]),
                                    generic: {pattern: RegExp(g), alias: "class-name", inside: _}
                                }
                            },
                            "type-list": {
                                pattern: a(/\b((?:<<0>>\s+<<1>>|where\s+<<2>>)\s*:\s*)(?:<<3>>|<<4>>)(?:\s*,\s*(?:<<3>>|<<4>>))*(?=\s*(?:where|[{;]|=>|$))/.source, [u, h, f, x, c.source]),
                                lookbehind: !0,
                                inside: {
                                    keyword: c,
                                    "class-name": {pattern: RegExp(x), greedy: !0, inside: _},
                                    punctuation: /,/
                                }
                            },
                            preprocessor: {
                                pattern: /(^\s*)#.*/m,
                                lookbehind: !0,
                                alias: "property",
                                inside: {
                                    directive: {
                                        pattern: /(\s*#)\b(?:define|elif|else|endif|endregion|error|if|line|pragma|region|undef|warning)\b/,
                                        lookbehind: !0,
                                        alias: "keyword"
                                    }
                                }
                            }
                        });
                        var S = F + "|" + P,
                            $ = r(/\/(?![*/])|\/\/[^\r\n]*[\r\n]|\/\*(?:[^*]|\*(?!\/))*\*\/|<<0>>/.source, [S]),
                            j = e(r(/[^"'/()]|<<0>>|\(<<self>>*\)/.source, [$]), 2),
                            E = /\b(?:assembly|event|field|method|module|param|property|return|type)\b/.source,
                            z = r(/<<0>>(?:\s*\(<<1>>*\))?/.source, [b, j]);
                        t.languages.insertBefore("csharp", "class-name", {
                            attribute: {
                                pattern: a(/((?:^|[^\s\w>)?])\s*\[\s*)(?:<<0>>\s*:\s*)?<<1>>(?:\s*,\s*<<1>>)*(?=\s*\])/.source, [E, z]),
                                lookbehind: !0,
                                greedy: !0,
                                inside: {
                                    target: {pattern: a(/^<<0>>(?=\s*:)/.source, [E]), alias: "keyword"},
                                    "attribute-arguments": {
                                        pattern: a(/\(<<0>>*\)/.source, [j]),
                                        inside: t.languages.csharp
                                    },
                                    "class-name": {pattern: RegExp(b), inside: {punctuation: /\./}},
                                    punctuation: /[:,]/
                                }
                            }
                        });
                        var C = /:[^}\r\n]+/.source, O = e(r(/[^"'/()]|<<0>>|\(<<self>>*\)/.source, [$]), 2),
                            N = r(/\{(?!\{)(?:(?![}:])<<0>>)*<<1>>?\}/.source, [O, C]),
                            B = e(r(/[^"'/()]|\/(?!\*)|\/\*(?:[^*]|\*(?!\/))*\*\/|<<0>>|\(<<self>>*\)/.source, [S]), 2),
                            T = r(/\{(?!\{)(?:(?![}:])<<0>>)*<<1>>?\}/.source, [B, C]);

                        function D(e, n) {
                            return {
                                interpolation: {
                                    pattern: a(/((?:^|[^{])(?:\{\{)*)<<0>>/.source, [e]),
                                    lookbehind: !0,
                                    inside: {
                                        "format-string": {
                                            pattern: a(/(^\{(?:(?![}:])<<0>>)*)<<1>>(?=\}$)/.source, [n, C]),
                                            lookbehind: !0,
                                            inside: {punctuation: /^:/}
                                        },
                                        punctuation: /^\{|\}$/,
                                        expression: {
                                            pattern: /[\s\S]+/,
                                            alias: "language-csharp",
                                            inside: t.languages.csharp
                                        }
                                    }
                                }, string: /[\s\S]+/
                            }
                        }

                        t.languages.insertBefore("csharp", "string", {
                            "interpolation-string": [{
                                pattern: a(/(^|[^\\])(?:\$@|@\$)"(?:""|\\[\s\S]|\{\{|<<0>>|[^\\{"])*"/.source, [N]),
                                lookbehind: !0,
                                greedy: !0,
                                inside: D(N, O)
                            }, {
                                pattern: a(/(^|[^@\\])\$"(?:\\.|\{\{|<<0>>|[^\\"{])*"/.source, [T]),
                                lookbehind: !0,
                                greedy: !0,
                                inside: D(T, B)
                            }]
                        })
                    }(Prism), Prism.languages.dotnet = Prism.languages.cs = Prism.languages.csharp
                }, {}], 6: [function (e, n, t) {
                    !function (e) {
                        var n = /("|')(?:\\(?:\r\n|[\s\S])|(?!\1)[^\\\r\n])*\1/;
                        e.languages.css = {
                            comment: /\/\*[\s\S]*?\*\//,
                            atrule: {
                                pattern: /@[\w-]+[\s\S]*?(?:;|(?=\s*\{))/,
                                inside: {
                                    rule: /^@[\w-]+/,
                                    "selector-function-argument": {
                                        pattern: /(\bselector\s*\((?!\s*\))\s*)(?:[^()]|\((?:[^()]|\([^()]*\))*\))+?(?=\s*\))/,
                                        lookbehind: !0,
                                        alias: "selector"
                                    },
                                    keyword: {pattern: /(^|[^\w-])(?:and|not|only|or)(?![\w-])/, lookbehind: !0}
                                }
                            },
                            url: {
                                pattern: RegExp("\\burl\\((?:" + n.source + "|" + /(?:[^\\\r\n()"']|\\[\s\S])*/.source + ")\\)", "i"),
                                greedy: !0,
                                inside: {
                                    "function": /^url/i,
                                    punctuation: /^\(|\)$/,
                                    string: {pattern: RegExp("^" + n.source + "$"), alias: "url"}
                                }
                            },
                            selector: RegExp("[^{}\\s](?:[^{};\"']|" + n.source + ")*?(?=\\s*\\{)"),
                            string: {pattern: n, greedy: !0},
                            property: /[-_a-z\xA0-\uFFFF][-\w\xA0-\uFFFF]*(?=\s*:)/i,
                            important: /!important\b/i,
                            "function": /[-a-z0-9]+(?=\()/i,
                            punctuation: /[(){};:,]/
                        }, e.languages.css.atrule.inside.rest = e.languages.css;
                        var t = e.languages.markup;
                        t && (t.tag.addInlined("style", "css"), e.languages.insertBefore("inside", "attr-value", {
                            "style-attr": {
                                pattern: /\s*style=("|')(?:\\[\s\S]|(?!\1)[^\\])*\1/i,
                                inside: {
                                    "attr-name": {pattern: /^\s*style/i, inside: t.tag.inside},
                                    punctuation: /^\s*=\s*['"]|['"]\s*$/,
                                    "attr-value": {pattern: /.+/i, inside: e.languages.css}
                                },
                                alias: "language-css"
                            }
                        }, t.tag))
                    }(Prism)
                }, {}], 7: [function (e, n, t) {
                    var r, a, s;
                    r = Prism, a = /\b(?:abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|exports|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|module|native|new|null|open|opens|package|private|protected|provides|public|record|requires|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|to|transient|transitive|try|uses|var|void|volatile|while|with|yield)\b/, s = /\b[A-Z](?:\w*[a-z]\w*)?\b/, r.languages.java = r.languages.extend("clike", {
                        "class-name": [s, /\b[A-Z]\w*(?=\s+\w+\s*[;,=())])/],
                        keyword: a,
                        "function": [r.languages.clike["function"], {pattern: /(\:\:)[a-z_]\w*/, lookbehind: !0}],
                        number: /\b0b[01][01_]*L?\b|\b0x[\da-f_]*\.?[\da-f_p+-]+\b|(?:\b\d[\d_]*\.?[\d_]*|\B\.\d[\d_]*)(?:e[+-]?\d[\d_]*)?[dfl]?/i,
                        operator: {
                            pattern: /(^|[^.])(?:<<=?|>>>?=?|->|--|\+\+|&&|\|\||::|[?:~]|[-+*/%&|^!=<>]=?)/m,
                            lookbehind: !0
                        }
                    }), r.languages.insertBefore("java", "string", {
                        "triple-quoted-string": {
                            pattern: /"""[ \t]*[\r\n](?:(?:"|"")?(?:\\.|[^"\\]))*"""/,
                            greedy: !0,
                            alias: "string"
                        }
                    }), r.languages.insertBefore("java", "class-name", {
                        annotation: {
                            alias: "punctuation",
                            pattern: /(^|[^.])@\w+/,
                            lookbehind: !0
                        },
                        namespace: {
                            pattern: RegExp(/(\b(?:exports|import(?:\s+static)?|module|open|opens|package|provides|requires|to|transitive|uses|with)\s+)(?!<keyword>)[a-z]\w*(?:\.[a-z]\w*)*\.?/.source.replace(/<keyword>/g, function () {
                                return a.source
                            })), lookbehind: !0, inside: {punctuation: /\./}
                        },
                        generics: {
                            pattern: /<(?:[\w\s,.&?]|<(?:[\w\s,.&?]|<(?:[\w\s,.&?]|<[\w\s,.&?]*>)*>)*>)*>/,
                            inside: {"class-name": s, keyword: a, punctuation: /[<>(),.:]/, operator: /[?&|]/}
                        }
                    })
                }, {}], 8: [function (e, n, t) {
                    Prism.languages.javascript = Prism.languages.extend("clike", {
                        "class-name": [Prism.languages.clike["class-name"], {
                            pattern: /(^|[^$\w\xA0-\uFFFF])[_$A-Z\xA0-\uFFFF][$\w\xA0-\uFFFF]*(?=\.(?:prototype|constructor))/,
                            lookbehind: !0
                        }],
                        keyword: [{
                            pattern: /((?:^|})\s*)(?:catch|finally)\b/,
                            lookbehind: !0
                        }, {
                            pattern: /(^|[^.]|\.\.\.\s*)\b(?:as|async(?=\s*(?:function\b|\(|[$\w\xA0-\uFFFF]|$))|await|break|case|class|const|continue|debugger|default|delete|do|else|enum|export|extends|for|from|function|(?:get|set)(?=\s*[\[$\w\xA0-\uFFFF])|if|implements|import|in|instanceof|interface|let|new|null|of|package|private|protected|public|return|static|super|switch|this|throw|try|typeof|undefined|var|void|while|with|yield)\b/,
                            lookbehind: !0
                        }],
                        number: /\b(?:(?:0[xX](?:[\dA-Fa-f](?:_[\dA-Fa-f])?)+|0[bB](?:[01](?:_[01])?)+|0[oO](?:[0-7](?:_[0-7])?)+)n?|(?:\d(?:_\d)?)+n|NaN|Infinity)\b|(?:\b(?:\d(?:_\d)?)+\.?(?:\d(?:_\d)?)*|\B\.(?:\d(?:_\d)?)+)(?:[Ee][+-]?(?:\d(?:_\d)?)+)?/,
                        "function": /#?[_$a-zA-Z\xA0-\uFFFF][$\w\xA0-\uFFFF]*(?=\s*(?:\.\s*(?:apply|bind|call)\s*)?\()/,
                        operator: /--|\+\+|\*\*=?|=>|&&=?|\|\|=?|[!=]==|<<=?|>>>?=?|[-+*/%&|^!=<>]=?|\.{3}|\?\?=?|\?\.?|[~:]/
                    }), Prism.languages.javascript["class-name"][0].pattern = /(\b(?:class|interface|extends|implements|instanceof|new)\s+)[\w.\\]+/, Prism.languages.insertBefore("javascript", "keyword", {
                        regex: {
                            pattern: /((?:^|[^$\w\xA0-\uFFFF."'\])\s]|\b(?:return|yield))\s*)\/(?:\[(?:[^\]\\\r\n]|\\.)*]|\\.|[^/\\\[\r\n])+\/[gimyus]{0,6}(?=(?:\s|\/\*(?:[^*]|\*(?!\/))*\*\/)*(?:$|[\r\n,.;:})\]]|\/\/))/,
                            lookbehind: !0,
                            greedy: !0,
                            inside: {
                                "regex-source": {
                                    pattern: /^(\/)[\s\S]+(?=\/[a-z]*$)/,
                                    lookbehind: !0,
                                    alias: "language-regex",
                                    inside: Prism.languages.regex
                                }, "regex-flags": /[a-z]+$/, "regex-delimiter": /^\/|\/$/
                            }
                        },
                        "function-variable": {
                            pattern: /#?[_$a-zA-Z\xA0-\uFFFF][$\w\xA0-\uFFFF]*(?=\s*[=:]\s*(?:async\s*)?(?:\bfunction\b|(?:\((?:[^()]|\([^()]*\))*\)|[_$a-zA-Z\xA0-\uFFFF][$\w\xA0-\uFFFF]*)\s*=>))/,
                            alias: "function"
                        },
                        parameter: [{
                            pattern: /(function(?:\s+[_$A-Za-z\xA0-\uFFFF][$\w\xA0-\uFFFF]*)?\s*\(\s*)(?!\s)(?:[^()]|\([^()]*\))+?(?=\s*\))/,
                            lookbehind: !0,
                            inside: Prism.languages.javascript
                        }, {
                            pattern: /[_$a-z\xA0-\uFFFF][$\w\xA0-\uFFFF]*(?=\s*=>)/i,
                            inside: Prism.languages.javascript
                        }, {
                            pattern: /(\(\s*)(?!\s)(?:[^()]|\([^()]*\))+?(?=\s*\)\s*=>)/,
                            lookbehind: !0,
                            inside: Prism.languages.javascript
                        }, {
                            pattern: /((?:\b|\s|^)(?!(?:as|async|await|break|case|catch|class|const|continue|debugger|default|delete|do|else|enum|export|extends|finally|for|from|function|get|if|implements|import|in|instanceof|interface|let|new|null|of|package|private|protected|public|return|set|static|super|switch|this|throw|try|typeof|undefined|var|void|while|with|yield)(?![$\w\xA0-\uFFFF]))(?:[_$A-Za-z\xA0-\uFFFF][$\w\xA0-\uFFFF]*\s*)\(\s*|\]\s*\(\s*)(?!\s)(?:[^()]|\([^()]*\))+?(?=\s*\)\s*\{)/,
                            lookbehind: !0,
                            inside: Prism.languages.javascript
                        }],
                        constant: /\b[A-Z](?:[A-Z_]|\dx?)*\b/
                    }), Prism.languages.insertBefore("javascript", "string", {
                        "template-string": {
                            pattern: /`(?:\\[\s\S]|\${(?:[^{}]|{(?:[^{}]|{[^}]*})*})+}|(?!\${)[^\\`])*`/,
                            greedy: !0,
                            inside: {
                                "template-punctuation": {pattern: /^`|`$/, alias: "string"},
                                interpolation: {
                                    pattern: /((?:^|[^\\])(?:\\{2})*)\${(?:[^{}]|{(?:[^{}]|{[^}]*})*})+}/,
                                    lookbehind: !0,
                                    inside: {
                                        "interpolation-punctuation": {pattern: /^\${|}$/, alias: "punctuation"},
                                        rest: Prism.languages.javascript
                                    }
                                },
                                string: /[\s\S]+/
                            }
                        }
                    }), Prism.languages.markup && Prism.languages.markup.tag.addInlined("script", "javascript"), Prism.languages.js = Prism.languages.javascript
                }, {}], 9: [function (e, n, t) {
                    function b(e, n) {
                        return "___" + e.toUpperCase() + n + "___"
                    }

                    var y;
                    y = Prism, Object.defineProperties(y.languages["markup-templating"] = {}, {
                        buildPlaceholders: {
                            value: function (r, a, e, s) {
                                var i;
                                r.language === a && (i = r.tokenStack = [], r.code = r.code.replace(e, function (e) {
                                    if ("function" == typeof s && !s(e)) return e;
                                    for (var n, t = i.length; -1 !== r.code.indexOf(n = b(a, t));) ++t;
                                    return i[t] = e, n
                                }), r.grammar = y.languages.markup)
                            }
                        }, tokenizePlaceholders: {
                            value: function (d, g) {
                                var m, f;
                                d.language === g && d.tokenStack && (d.grammar = y.languages[g], m = 0, f = Object.keys(d.tokenStack), function h(e) {
                                    for (var n = 0; n < e.length && !(m >= f.length); n++) {
                                        var t, r, a, s, i, o, l, u, c, p = e[n];
                                        "string" == typeof p || p.content && "string" == typeof p.content ? (t = f[m], r = d.tokenStack[t], a = "string" == typeof p ? p : p.content, s = b(g, t), -1 < (i = a.indexOf(s)) && (++m, o = a.substring(0, i), l = new y.Token(g, y.tokenize(r, d.grammar), "language-" + g, r), u = a.substring(i + s.length), c = [], o && c.push.apply(c, h([o])), c.push(l), u && c.push.apply(c, h([u])), "string" == typeof p ? e.splice.apply(e, [n, 1].concat(c)) : p.content = c)) : p.content && h(p.content)
                                    }
                                    return e
                                }(d.tokens))
                            }
                        }
                    })
                }, {}], 10: [function (e, n, t) {
                    Prism.languages.markup = {
                        comment: /<!--[\s\S]*?-->/,
                        prolog: /<\?[\s\S]+?\?>/,
                        doctype: {
                            pattern: /<!DOCTYPE(?:[^>"'[\]]|"[^"]*"|'[^']*')+(?:\[(?:[^<"'\]]|"[^"]*"|'[^']*'|<(?!!--)|<!--(?:[^-]|-(?!->))*-->)*\]\s*)?>/i,
                            greedy: !0,
                            inside: {
                                "internal-subset": {
                                    pattern: /(\[)[\s\S]+(?=\]>$)/,
                                    lookbehind: !0,
                                    greedy: !0,
                                    inside: null
                                },
                                string: {pattern: /"[^"]*"|'[^']*'/, greedy: !0},
                                punctuation: /^<!|>$|[[\]]/,
                                "doctype-tag": /^DOCTYPE/,
                                name: /[^\s<>'"]+/
                            }
                        },
                        cdata: /<!\[CDATA\[[\s\S]*?]]>/i,
                        tag: {
                            pattern: /<\/?(?!\d)[^\s>\/=$<%]+(?:\s(?:\s*[^\s>\/=]+(?:\s*=\s*(?:"[^"]*"|'[^']*'|[^\s'">=]+(?=[\s>]))|(?=[\s/>])))+)?\s*\/?>/,
                            greedy: !0,
                            inside: {
                                tag: {
                                    pattern: /^<\/?[^\s>\/]+/,
                                    inside: {punctuation: /^<\/?/, namespace: /^[^\s>\/:]+:/}
                                },
                                "attr-value": {
                                    pattern: /=\s*(?:"[^"]*"|'[^']*'|[^\s'">=]+)/,
                                    inside: {punctuation: [{pattern: /^=/, alias: "attr-equals"}, /"|'/]}
                                },
                                punctuation: /\/?>/,
                                "attr-name": {pattern: /[^\s>\/]+/, inside: {namespace: /^[^\s>\/:]+:/}}
                            }
                        },
                        entity: [{pattern: /&[\da-z]{1,8};/i, alias: "named-entity"}, /&#x?[\da-f]{1,8};/i]
                    }, Prism.languages.markup.tag.inside["attr-value"].inside.entity = Prism.languages.markup.entity, Prism.languages.markup.doctype.inside["internal-subset"].inside = Prism.languages.markup, Prism.hooks.add("wrap", function (e) {
                        "entity" === e.type && (e.attributes.title = e.content.replace(/&amp;/, "&"))
                    }), Object.defineProperty(Prism.languages.markup.tag, "addInlined", {
                        value: function (e, n) {
                            var t = {};
                            t["language-" + n] = {
                                pattern: /(^<!\[CDATA\[)[\s\S]+?(?=\]\]>$)/i,
                                lookbehind: !0,
                                inside: Prism.languages[n]
                            }, t.cdata = /^<!\[CDATA\[|\]\]>$/i;
                            var r = {"included-cdata": {pattern: /<!\[CDATA\[[\s\S]*?\]\]>/i, inside: t}};
                            r["language-" + n] = {pattern: /[\s\S]+/, inside: Prism.languages[n]};
                            var a = {};
                            a[e] = {
                                pattern: RegExp(/(<__[\s\S]*?>)(?:<!\[CDATA\[(?:[^\]]|\](?!\]>))*\]\]>|(?!<!\[CDATA\[)[\s\S])*?(?=<\/__>)/.source.replace(/__/g, function () {
                                    return e
                                }), "i"), lookbehind: !0, greedy: !0, inside: r
                            }, Prism.languages.insertBefore("markup", "cdata", a)
                        }
                    }), Prism.languages.html = Prism.languages.markup, Prism.languages.mathml = Prism.languages.markup, Prism.languages.svg = Prism.languages.markup, Prism.languages.xml = Prism.languages.extend("markup", {}), Prism.languages.ssml = Prism.languages.xml, Prism.languages.atom = Prism.languages.xml, Prism.languages.rss = Prism.languages.xml
                }, {}], 11: [function (e, n, t) {
                    !function (n) {
                        n.languages.php = n.languages.extend("clike", {
                            keyword: /\b(?:__halt_compiler|abstract|and|array|as|break|callable|case|catch|class|clone|const|continue|declare|default|die|do|echo|else|elseif|empty|enddeclare|endfor|endforeach|endif|endswitch|endwhile|eval|exit|extends|final|finally|for|foreach|function|global|goto|if|implements|include|include_once|instanceof|insteadof|interface|isset|list|match|namespace|new|or|parent|print|private|protected|public|require|require_once|return|static|switch|throw|trait|try|unset|use|var|while|xor|yield)\b/i,
                            "boolean": {pattern: /\b(?:false|true)\b/i, alias: "constant"},
                            constant: [/\b[A-Z_][A-Z0-9_]*\b/, /\b(?:null)\b/i],
                            comment: {pattern: /(^|[^\\])(?:\/\*[\s\S]*?\*\/|\/\/.*)/, lookbehind: !0}
                        }), n.languages.insertBefore("php", "string", {
                            "shell-comment": {
                                pattern: /(^|[^\\])#.*/,
                                lookbehind: !0,
                                alias: "comment"
                            }
                        }), n.languages.insertBefore("php", "comment", {
                            delimiter: {
                                pattern: /\?>$|^<\?(?:php(?=\s)|=)?/i,
                                alias: "important"
                            }
                        }), n.languages.insertBefore("php", "keyword", {
                            variable: /\$+(?:\w+\b|(?={))/i,
                            "package": {
                                pattern: /(\\|namespace\s+|use\s+)[\w\\]+/,
                                lookbehind: !0,
                                inside: {punctuation: /\\/}
                            }
                        }), n.languages.insertBefore("php", "operator", {
                            property: {
                                pattern: /(->)[\w]+/,
                                lookbehind: !0
                            }
                        });
                        var e = {
                            pattern: /{\$(?:{(?:{[^{}]+}|[^{}]+)}|[^{}])+}|(^|[^\\{])\$+(?:\w+(?:\[[^\r\n\[\]]+\]|->\w+)*)/,
                            lookbehind: !0,
                            inside: n.languages.php
                        };
                        n.languages.insertBefore("php", "string", {
                            "nowdoc-string": {
                                pattern: /<<<'([^']+)'[\r\n](?:.*[\r\n])*?\1;/,
                                greedy: !0,
                                alias: "string",
                                inside: {
                                    delimiter: {
                                        pattern: /^<<<'[^']+'|[a-z_]\w*;$/i,
                                        alias: "symbol",
                                        inside: {punctuation: /^<<<'?|[';]$/}
                                    }
                                }
                            },
                            "heredoc-string": {
                                pattern: /<<<(?:"([^"]+)"[\r\n](?:.*[\r\n])*?\1;|([a-z_]\w*)[\r\n](?:.*[\r\n])*?\2;)/i,
                                greedy: !0,
                                alias: "string",
                                inside: {
                                    delimiter: {
                                        pattern: /^<<<(?:"[^"]+"|[a-z_]\w*)|[a-z_]\w*;$/i,
                                        alias: "symbol",
                                        inside: {punctuation: /^<<<"?|[";]$/}
                                    }, interpolation: e
                                }
                            },
                            "single-quoted-string": {pattern: /'(?:\\[\s\S]|[^\\'])*'/, greedy: !0, alias: "string"},
                            "double-quoted-string": {
                                pattern: /"(?:\\[\s\S]|[^\\"])*"/,
                                greedy: !0,
                                alias: "string",
                                inside: {interpolation: e}
                            }
                        }), delete n.languages.php.string, n.hooks.add("before-tokenize", function (e) {
                            /<\?/.test(e.code) && n.languages["markup-templating"].buildPlaceholders(e, "php", /<\?(?:[^"'/#]|\/(?![*/])|("|')(?:\\[\s\S]|(?!\1)[^\\])*\1|(?:\/\/|#)(?:[^?\n\r]|\?(?!>))*(?=$|\?>|[\r\n])|\/\*[\s\S]*?(?:\*\/|$))*?(?:\?>|$)/gi)
                        }), n.hooks.add("after-tokenize", function (e) {
                            n.languages["markup-templating"].tokenizePlaceholders(e, "php")
                        })
                    }(Prism)
                }, {}], 12: [function (e, n, t) {
                    Prism.languages.python = {
                        comment: {pattern: /(^|[^\\])#.*/, lookbehind: !0},
                        "string-interpolation": {
                            pattern: /(?:f|rf|fr)(?:("""|''')[\s\S]*?\1|("|')(?:\\.|(?!\2)[^\\\r\n])*\2)/i,
                            greedy: !0,
                            inside: {
                                interpolation: {
                                    pattern: /((?:^|[^{])(?:{{)*){(?!{)(?:[^{}]|{(?!{)(?:[^{}]|{(?!{)(?:[^{}])+})+})+}/,
                                    lookbehind: !0,
                                    inside: {
                                        "format-spec": {pattern: /(:)[^:(){}]+(?=}$)/, lookbehind: !0},
                                        "conversion-option": {pattern: /![sra](?=[:}]$)/, alias: "punctuation"},
                                        rest: null
                                    }
                                }, string: /[\s\S]+/
                            }
                        },
                        "triple-quoted-string": {
                            pattern: /(?:[rub]|rb|br)?("""|''')[\s\S]*?\1/i,
                            greedy: !0,
                            alias: "string"
                        },
                        string: {pattern: /(?:[rub]|rb|br)?("|')(?:\\.|(?!\1)[^\\\r\n])*\1/i, greedy: !0},
                        "function": {pattern: /((?:^|\s)def[ \t]+)[a-zA-Z_]\w*(?=\s*\()/g, lookbehind: !0},
                        "class-name": {pattern: /(\bclass\s+)\w+/i, lookbehind: !0},
                        decorator: {
                            pattern: /(^\s*)@\w+(?:\.\w+)*/im,
                            lookbehind: !0,
                            alias: ["annotation", "punctuation"],
                            inside: {punctuation: /\./}
                        },
                        keyword: /\b(?:and|as|assert|async|await|break|class|continue|def|del|elif|else|except|exec|finally|for|from|global|if|import|in|is|lambda|nonlocal|not|or|pass|print|raise|return|try|while|with|yield)\b/,
                        builtin: /\b(?:__import__|abs|all|any|apply|ascii|basestring|bin|bool|buffer|bytearray|bytes|callable|chr|classmethod|cmp|coerce|compile|complex|delattr|dict|dir|divmod|enumerate|eval|execfile|file|filter|float|format|frozenset|getattr|globals|hasattr|hash|help|hex|id|input|int|intern|isinstance|issubclass|iter|len|list|locals|long|map|max|memoryview|min|next|object|oct|open|ord|pow|property|range|raw_input|reduce|reload|repr|reversed|round|set|setattr|slice|sorted|staticmethod|str|sum|super|tuple|type|unichr|unicode|vars|xrange|zip)\b/,
                        "boolean": /\b(?:True|False|None)\b/,
                        number: /(?:\b(?=\d)|\B(?=\.))(?:0[bo])?(?:(?:\d|0x[\da-f])[\da-f]*\.?\d*|\.\d+)(?:e[+-]?\d+)?j?\b/i,
                        operator: /[-+%=]=?|!=|\*\*?=?|\/\/?=?|<[<=>]?|>[=>]?|[&|^~]/,
                        punctuation: /[{}[\];(),.:]/
                    }, Prism.languages.python["string-interpolation"].inside.interpolation.inside.rest = Prism.languages.python, Prism.languages.py = Prism.languages.python
                }, {}], 13: [function (e, n, t) {
                    !function (e) {
                        e.languages.ruby = e.languages.extend("clike", {
                            comment: [/#.*/, {
                                pattern: /^=begin\s[\s\S]*?^=end/m,
                                greedy: !0
                            }],
                            "class-name": {
                                pattern: /(\b(?:class)\s+|\bcatch\s+\()[\w.\\]+/i,
                                lookbehind: !0,
                                inside: {punctuation: /[.\\]/}
                            },
                            keyword: /\b(?:alias|and|BEGIN|begin|break|case|class|def|define_method|defined|do|each|else|elsif|END|end|ensure|extend|for|if|in|include|module|new|next|nil|not|or|prepend|protected|private|public|raise|redo|require|rescue|retry|return|self|super|then|throw|undef|unless|until|when|while|yield)\b/
                        });
                        var n = {
                            pattern: /#\{[^}]+\}/,
                            inside: {delimiter: {pattern: /^#\{|\}$/, alias: "tag"}, rest: e.languages.ruby}
                        };
                        delete e.languages.ruby["function"], e.languages.insertBefore("ruby", "keyword", {
                            regex: [{
                                pattern: RegExp(/%r/.source + "(?:" + [/([^a-zA-Z0-9\s{(\[<])(?:(?!\1)[^\\]|\\[\s\S])*\1[gim]{0,3}/.source, /\((?:[^()\\]|\\[\s\S])*\)[gim]{0,3}/.source, /\{(?:[^#{}\\]|#(?:\{[^}]+\})?|\\[\s\S])*\}[gim]{0,3}/.source, /\[(?:[^\[\]\\]|\\[\s\S])*\][gim]{0,3}/.source, /<(?:[^<>\\]|\\[\s\S])*>[gim]{0,3}/.source].join("|") + ")"),
                                greedy: !0,
                                inside: {interpolation: n}
                            }, {
                                pattern: /(^|[^/])\/(?!\/)(?:\[[^\r\n\]]+\]|\\.|[^[/\\\r\n])+\/[gim]{0,3}(?=\s*(?:$|[\r\n,.;})]))/,
                                lookbehind: !0,
                                greedy: !0
                            }],
                            variable: /[@$]+[a-zA-Z_]\w*(?:[?!]|\b)/,
                            symbol: {pattern: /(^|[^:]):[a-zA-Z_]\w*(?:[?!]|\b)/, lookbehind: !0},
                            "method-definition": {
                                pattern: /(\bdef\s+)[\w.]+/,
                                lookbehind: !0,
                                inside: {"function": /\w+$/, rest: e.languages.ruby}
                            }
                        }), e.languages.insertBefore("ruby", "number", {
                            builtin: /\b(?:Array|Bignum|Binding|Class|Continuation|Dir|Exception|FalseClass|File|Stat|Fixnum|Float|Hash|Integer|IO|MatchData|Method|Module|NilClass|Numeric|Object|Proc|Range|Regexp|String|Struct|TMS|Symbol|ThreadGroup|Thread|Time|TrueClass)\b/,
                            constant: /\b[A-Z]\w*(?:[?!]|\b)/
                        }), e.languages.ruby.string = [{
                            pattern: RegExp(/%[qQiIwWxs]?/.source + "(?:" + [/([^a-zA-Z0-9\s{(\[<])(?:(?!\1)[^\\]|\\[\s\S])*\1/.source, /\((?:[^()\\]|\\[\s\S])*\)/.source, /\{(?:[^#{}\\]|#(?:\{[^}]+\})?|\\[\s\S])*\}/.source, /\[(?:[^\[\]\\]|\\[\s\S])*\]/.source, /<(?:[^<>\\]|\\[\s\S])*>/.source].join("|") + ")"),
                            greedy: !0,
                            inside: {interpolation: n}
                        }, {
                            pattern: /("|')(?:#\{[^}]+\}|\\(?:\r\n|[\s\S])|(?!\1)[^\\\r\n])*\1/,
                            greedy: !0,
                            inside: {interpolation: n}
                        }], e.languages.rb = e.languages.ruby
                    }(Prism)
                }, {}], 14: [function (e, n, t) {
                    var r = e("prismjs/components/prism-core");
                    e("prismjs/components/prism-clike"), e("prismjs/components/prism-markup-templating"), e("prismjs/components/prism-c"), e("prismjs/components/prism-cpp"), e("prismjs/components/prism-csharp"), e("prismjs/components/prism-css"), e("prismjs/components/prism-java"), e("prismjs/components/prism-javascript"), e("prismjs/components/prism-markup"), e("prismjs/components/prism-php"), e("prismjs/components/prism-python"), e("prismjs/components/prism-ruby"), n.exports = {boltExport: r}
                }, {
                    "prismjs/components/prism-c": 1,
                    "prismjs/components/prism-clike": 2,
                    "prismjs/components/prism-core": 3,
                    "prismjs/components/prism-cpp": 4,
                    "prismjs/components/prism-csharp": 5,
                    "prismjs/components/prism-css": 6,
                    "prismjs/components/prism-java": 7,
                    "prismjs/components/prism-javascript": 8,
                    "prismjs/components/prism-markup": 10,
                    "prismjs/components/prism-markup-templating": 9,
                    "prismjs/components/prism-php": 11,
                    "prismjs/components/prism-python": 12,
                    "prismjs/components/prism-ruby": 13
                }]
            }, {}, [14])(14)
        });
        var a = window.Prism;
        window.Prism = e
    }(undefined, h, b, undefined);
    var v = b.exports.boltExport, w = function (e) {
        return f.Prism && e.getParam("codesample_global_prismjs", !1, "boolean") ? f.Prism : v
    }, k = function (e) {
        var n = e.selection ? e.selection.getNode() : null;
        return g(n) ? c.some(n) : c.none()
    }, x = function (s) {
        var t, e = s.getParam("codesample_languages") || [{text: "HTML/XML", value: "markup"}, {
            text: "JavaScript",
            value: "javascript"
        }, {text: "CSS", value: "css"}, {text: "PHP", value: "php"}, {text: "Ruby", value: "ruby"}, {
            text: "Python",
            value: "python"
        }, {text: "Java", value: "java"}, {text: "C", value: "c"}, {text: "C#", value: "csharp"}, {
            text: "C++",
            value: "cpp"
        }], n = p(e).fold(function () {
            return ""
        }, function (e) {
            return e.value
        }), r = (t = n, k(s).fold(function () {
            return t
        }, function (e) {
            var n = e.className.match(/language-(\w+)/);
            return n ? n[1] : t
        })), a = k(s).fold(function () {
            return ""
        }, function (e) {
            return e.textContent
        });
        s.windowManager.open({
            title: "Insert/Edit Code Sample",
            size: "large",
            body: {
                type: "panel",
                items: [{type: "selectbox", name: "language", label: "Language", items: e}, {
                    type: "textarea",
                    name: "code",
                    label: "Code view"
                }]
            },
            buttons: [{type: "cancel", name: "cancel", text: "Cancel"}, {
                type: "submit",
                name: "save",
                text: "Save",
                primary: !0
            }],
            initialData: {language: r, code: a},
            onSubmit: function (e) {
                var n, t, r, a = e.getData();
                n = s, t = a.language, r = a.code, n.undoManager.transact(function () {
                    var e = k(n);
                    return r = d.DOM.encode(r), e.fold(function () {
                        n.insertContent('<pre id="__new" class="language-' + t + '">' + r + "</pre>"), n.selection.select(n.$("#__new").removeAttr("id")[0])
                    }, function (e) {
                        n.dom.setAttrib(e, "class", "language-" + t), e.innerHTML = r, w(n).highlightElement(e), n.selection.select(e)
                    })
                }), e.close()
            }
        })
    }, _ = function (r) {
        r.ui.registry.addToggleButton("codesample", {
            icon: "code-sample",
            tooltip: "Insert/edit code sample",
            onAction: function () {
                return x(r)
            },
            onSetup: function (t) {
                var e = function () {
                    var e, n;
                    t.setActive((n = (e = r).selection.getStart(), e.dom.is(n, 'pre[class*="language-"]')))
                };
                return r.on("NodeChange", e), function () {
                    return r.off("NodeChange", e)
                }
            }
        }), r.ui.registry.addMenuItem("codesample", {
            text: "Code sample...",
            icon: "code-sample",
            onAction: function () {
                return x(r)
            }
        })
    };
    r.add("codesample", function (n) {
        var t, a, r;
        a = (t = n).$, t.on("PreProcess", function (e) {
            a("pre[contenteditable=false]", e.node).filter(m(g)).each(function (e, n) {
                var t = a(n), r = n.textContent;
                t.attr("class", a.trim(t.attr("class"))), t.removeAttr("contentEditable"), t.empty().append(a("<code></code>").each(function () {
                    this.textContent = r
                }))
            })
        }), t.on("SetContent", function () {
            var e = a("pre").filter(m(g)).filter(function (e, n) {
                return "false" !== n.contentEditable
            });
            e.length && t.undoManager.transact(function () {
                e.each(function (e, n) {
                    a(n).find("br").each(function (e, n) {
                        n.parentNode.replaceChild(t.getDoc().createTextNode("\n"), n)
                    }), n.contentEditable = "false", n.innerHTML = t.dom.encode(n.textContent), w(t).highlightElement(n), n.className = a.trim(n.className)
                })
            })
        }), _(n), (r = n).addCommand("codesample", function () {
            var e = r.selection.getNode();
            r.selection.isCollapsed() || g(e) ? x(r) : r.formatter.toggle("code")
        }), n.on("dblclick", function (e) {
            g(e.target) && x(n)
        })
    })
}();