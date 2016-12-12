/* Minification failed. Returning unminified contents.
(3287,1-2): run-time error JS1195: Expected expression: <
 */
var unityObject = {
    javaInstallDone: function (d, a, b) {
        var c = parseInt(d.substring(d.lastIndexOf("_") + 1), 10);
        if (!isNaN(c)) {
            setTimeout(function () {
                UnityObject2.instances[c].javaInstallDoneCallback(d, a, b)
            }, 10)
        }
    }
};
var UnityObject2 = function (J) {
    var ac = [],
        i = window,
        Y = document,
        W = navigator,
        E = null,
        h = [],
        af = (document.location.protocol == "https:"),
        y = af ? "https://ssl-webplayer.unity3d.com/" : "http://webplayer.unity3d.com/",
        K = "_unity_triedjava",
        G = a(K),
        r = "_unity_triedclickonce",
        u = a(r),
        aa = false,
        B = [],
        O = false,
        w = null,
        f = null,
        P = null,
        l = [],
        T = null,
        q = [],
        V = false,
        U = "installed",
        L = "missing",
        b = "broken",
        v = "unsupported",
        C = "ready",
        z = "start",
        F = "error",
        Z = "first",
        A = "java",
        s = "clickonce",
        M = false,
        R = null,
        x = {
            pluginName: "Unity Player",
            pluginMimeType: "application/vnd.unity",
            baseDownloadUrl: y + "download_webplayer-3.x/",
            fullInstall: false,
            autoInstall: false,
            enableJava: true,
            enableJVMPreloading: false,
            enableClickOnce: true,
            enableUnityAnalytics: false,
            enableGoogleAnalytics: true,
            params: {},
            attributes: {},
            referrer: null,
            debugLevel: 0
        };
    x = jQuery.extend(true, x, J);
    if (x.referrer === "") {
        x.referrer = null
    }
    if (af) {
        x.enableUnityAnalytics = false
    }

    function a(ag) {
        var ah = new RegExp(escape(ag) + "=([^;]+)");
        if (ah.test(Y.cookie + ";")) {
            ah.exec(Y.cookie + ";");
            return RegExp.$1
        }
        return false
    }

    function e(ag, ah) {
        document.cookie = escape(ag) + "=" + escape(ah) + "; path=/"
    }

    function N(am) {
        var an = 0,
            ai, al, aj, ag, ah;
        if (am) {
            var ak = am.toLowerCase().match(/^(\d+)(?:\.(\d+)(?:\.(\d+)([dabfr])?(\d+)?)?)?$/);
            if (ak && ak[1]) {
                ai = ak[1];
                al = ak[2] ? ak[2] : 0;
                aj = ak[3] ? ak[3] : 0;
                ag = ak[4] ? ak[4] : "r";
                ah = ak[5] ? ak[5] : 0;
                an |= ((ai / 10) % 10) << 28;
                an |= (ai % 10) << 24;
                an |= (al % 10) << 20;
                an |= (aj % 10) << 16;
                an |= {
                    d: 2 << 12,
                    a: 4 << 12,
                    b: 6 << 12,
                    f: 8 << 12,
                    r: 8 << 12
                }[ag];
                an |= ((ah / 100) % 10) << 8;
                an |= ((ah / 10) % 10) << 4;
                an |= (ah % 10)
            }
        }
        return an
    }

    function ae(al, ag) {
        var ai = Y.getElementsByTagName("body")[0];
        var ah = Y.createElement("object");
        var aj = 0;
        if (ai && ah) {
            ah.setAttribute("type", x.pluginMimeType);
            ah.style.visibility = "hidden";
            ai.appendChild(ah);
            var ak = 0;
            (function () {
                if (typeof ah.GetPluginVersion === "undefined") {
                    if (ak++ < 10) {
                        setTimeout(arguments.callee, 10)
                    } else {
                        ai.removeChild(ah);
                        al(null)
                    }
                } else {
                    var am = {};
                    if (ag) {
                        for (aj = 0; aj < ag.length; ++aj) {
                            am[ag[aj]] = ah.GetUnityVersion(ag[aj])
                        }
                    }
                    am.plugin = ah.GetPluginVersion();
                    ai.removeChild(ah);
                    al(am)
                }
            })()
        } else {
            al(null)
        }
    }

    function c() {
        var ag = "";
        if (t.x64) {
            ag = x.fullInstall ? "UnityWebPlayerFull64.exe" : "UnityWebPlayer64.exe"
        } else {
            ag = x.fullInstall ? "UnityWebPlayerFull.exe" : "UnityWebPlayer.exe"
        }
        if (x.referrer !== null) {
            ag += "?referrer=" + x.referrer
        }
        return ag
    }

    function ab() {
        var ag = "UnityPlayer.plugin.zip";
        if (x.referrer != null) {
            ag += "?referrer=" + x.referrer
        }
        return ag
    }

    function m() {
        return x.baseDownloadUrl + (t.win ? c() : ab())
    }

    function D(ai, ah, aj, ag) {
        if (ai === L) {
            M = true
        }
        if (jQuery.inArray(ai, q) === -1) {
            if (M) {
                j.send(ai, ah, aj, ag)
            }
            q.push(ai)
        }
        T = ai
    }
    var t = function () {
        var ai = W.userAgent,
            ak = W.platform;
        var am = /chrome/i.test(ai);
        var al = false;
        if (/msie/i.test(ai)) {
            al = parseFloat(ai.replace(/^.*msie ([0-9]+(\.[0-9]+)?).*$/i, "$1"))
        } else {
            if (/Trident/i.test(ai)) {
                al = parseFloat(ai.replace(/^.*rv:([0-9]+(\.[0-9]+)?).*$/i, "$1"))
            }
        }
        var an = {
            w3: typeof Y.getElementById != "undefined" && typeof Y.getElementsByTagName != "undefined" && typeof Y.createElement != "undefined",
            win: ak ? /win/i.test(ak) : /win/i.test(ai),
            mac: ak ? /mac/i.test(ak) : /mac/i.test(ai),
            ie: al,
            ff: /firefox/i.test(ai),
            op: /opera/i.test(ai),
            ch: am,
            ch_v: /chrome/i.test(ai) ? parseFloat(ai.replace(/^.*chrome\/(\d+(\.\d+)?).*$/i, "$1")) : false,
            sf: /safari/i.test(ai) && !am,
            wk: /webkit/i.test(ai) ? parseFloat(ai.replace(/^.*webkit\/(\d+(\.\d+)?).*$/i, "$1")) : false,
            x64: /win64/i.test(ai) && /x64/i.test(ai),
            moz: /mozilla/i.test(ai) ? parseFloat(ai.replace(/^.*mozilla\/([0-9]+(\.[0-9]+)?).*$/i, "$1")) : 0,
            mobile: /ipad/i.test(ak) || /iphone/i.test(ak) || /ipod/i.test(ak) || /android/i.test(ai) || /windows phone/i.test(ai)
        };
        an.clientBrand = an.ch ? "ch" : an.ff ? "ff" : an.sf ? "sf" : an.ie ? "ie" : an.op ? "op" : "??";
        an.clientPlatform = an.win ? "win" : an.mac ? "mac" : "???";
        var ao = Y.getElementsByTagName("script");
        for (var ag = 0; ag < ao.length; ++ag) {
            var aj = ao[ag].src.match(/^(.*)3\.0\/uo\/UnityObject2\.js$/i);
            if (aj) {
                x.baseDownloadUrl = aj[1];
                break
            }
        }

        function ah(ar, aq) {
            for (var at = 0; at < Math.max(ar.length, aq.length) ; ++at) {
                var ap = (at < ar.length) && ar[at] ? new Number(ar[at]) : 0;
                var au = (at < aq.length) && aq[at] ? new Number(aq[at]) : 0;
                if (ap < au) {
                    return -1
                }
                if (ap > au) {
                    return 1
                }
            }
            return 0
        }
        an.java = function () {
            if (W.javaEnabled()) {
                var at = (an.win && an.ff);
                var aw = false;
                if (at || aw) {
                    if (typeof W.mimeTypes != "undefined") {
                        var av = at ? [1, 6, 0, 12] : [1, 4, 2, 0];
                        for (var ar = 0; ar < W.mimeTypes.length; ++ar) {
                            if (W.mimeTypes[ar].enabledPlugin) {
                                var ap = W.mimeTypes[ar].type.match(/^application\/x-java-applet;(?:jpi-)?version=(\d+)(?:\.(\d+)(?:\.(\d+)(?:_(\d+))?)?)?$/);
                                if (ap != null) {
                                    if (ah(av, ap.slice(1)) <= 0) {
                                        return true
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (an.win && an.ie) {
                        if (typeof ActiveXObject != "undefined") {
                            function aq(ax) {
                                try {
                                    return new ActiveXObject("JavaWebStart.isInstalled." + ax + ".0") != null
                                } catch (ay) {
                                    return false
                                }
                            }

                            function au(ax) {
                                try {
                                    return new ActiveXObject("JavaPlugin.160_" + ax) != null
                                } catch (ay) {
                                    return false
                                }
                            }
                            if (aq("1.7.0")) {
                                return true
                            }
                            if (an.ie >= 8) {
                                if (aq("1.6.0")) {
                                    for (var ar = 12; ar <= 50; ++ar) {
                                        if (au(ar)) {
                                            if (an.ie == 9 && an.moz == 5 && ar < 24) {
                                                continue
                                            } else {
                                                return true
                                            }
                                        }
                                    }
                                    return false
                                }
                            } else {
                                return aq("1.6.0") || aq("1.5.0") || aq("1.4.2")
                            }
                        }
                    }
                }
            }
            return false
        }();
        an.co = function () {
            if (an.win && an.ie) {
                var ap = ai.match(/(\.NET CLR [0-9.]+)|(\.NET[0-9.]+)/g);
                if (ap != null) {
                    var at = [3, 5, 0];
                    for (var ar = 0; ar < ap.length; ++ar) {
                        var aq = ap[ar].match(/[0-9.]{2,}/g)[0].split(".");
                        if (ah(at, aq) <= 0) {
                            return true
                        }
                    }
                }
            }
            return false
        }();
        return an
    }();
    var j = function () {
        var ag = function () {
            var ao = new Date();
            var an = Date.UTC(ao.getUTCFullYear(), ao.getUTCMonth(), ao.getUTCDay(), ao.getUTCHours(), ao.getUTCMinutes(), ao.getUTCSeconds(), ao.getUTCMilliseconds());
            return an.toString(16) + am().toString(16)
        }();
        var ai = 0;
        var ah = window._gaq = (window._gaq || []);
        ak();

        function am() {
            return Math.floor(Math.random() * 2147483647)
        }

        function ak() {
            var at = ("https:" == document.location.protocol ? "https://ssl" : "http://www") + ".google-analytics.com/ga.js";
            var ap = Y.getElementsByTagName("script");
            var au = false;
            for (var ar = 0; ar < ap.length; ++ar) {
                if (ap[ar].src && ap[ar].src.toLowerCase() == at.toLowerCase()) {
                    au = true;
                    break
                }
            }
            if (!au) {
                var aq = Y.createElement("script");
                aq.type = "text/javascript";
                aq.async = true;
                aq.src = at;
                var ao = document.getElementsByTagName("script")[0];
                ao.parentNode.insertBefore(aq, ao)
            }
            var an = (x.debugLevel === 0) ? "UA-16068464-16" : "UA-16068464-17";
            ah.push(["unity._setDomainName", "none"]);
            ah.push(["unity._setAllowLinker", true]);
            ah.push(["unity._setReferrerOverride", " " + this.location.toString()]);
            ah.push(["unity._setAccount", an]);
            ah.push(["unity._setCustomVar", 1, "Revision", "fca41a4fabea", 2])
        }

        function aj(ar, ap, at, ao) {
            if (!x.enableUnityAnalytics) {
                if (ao) {
                    ao()
                }
                return
            }
            var an = "http://unityanalyticscapture.appspot.com/event?u=" + encodeURIComponent(ag) + "&s=" + encodeURIComponent(ai) + "&e=" + encodeURIComponent(ar);
            an += "&v=" + encodeURIComponent("fca41a4fabea");
            if (x.referrer !== null) {
                an += "?r=" + x.referrer
            }
            if (ap) {
                an += "&t=" + encodeURIComponent(ap)
            }
            if (at) {
                an += "&d=" + encodeURIComponent(at)
            }
            var aq = new Image();
            if (ao) {
                aq.onload = aq.onerror = ao
            }
            aq.src = an
        }

        function al(ap, an, aq, ay) {
            if (!x.enableGoogleAnalytics) {
                if (ay) {
                    ay()
                }
                return
            }
            var av = "/webplayer/install/" + ap;
            var aw = "?";
            if (an) {
                av += aw + "t=" + encodeURIComponent(an);
                aw = "&"
            }
            if (aq) {
                av += aw + "d=" + encodeURIComponent(aq);
                aw = "&"
            }
            if (ay) {
                ah.push(function () {
                    setTimeout(ay, 1000)
                })
            }
            var at = x.src;
            if (at.length > 40) {
                at = at.replace("http://", "");
                var ao = at.split("/");
                var ax = ao.shift();
                var ar = ao.pop();
                at = ax + "/../" + ar;
                while (at.length < 40 && ao.length > 0) {
                    var au = ao.pop();
                    if (at.length + au.length + 5 < 40) {
                        ar = au + "/" + ar
                    } else {
                        ar = "../" + ar
                    }
                    at = ax + "/../" + ar
                }
            }
            ah.push(["unity._setCustomVar", 2, "GameURL", at, 3]);
            ah.push(["unity._setCustomVar", 1, "UnityObjectVersion", "2", 3]);
            if (an) {
                ah.push(["unity._setCustomVar", 3, "installMethod", an, 3])
            }
            ah.push(["unity._trackPageview", av])
        }
        return {
            send: function (aq, ap, at, an) {
                if (x.enableUnityAnalytics || x.enableGoogleAnalytics) {
                    n("Analytics SEND", aq, ap, at, an)
                } ++ai;
                var ar = 2;
                var ao = function () {
                    if (0 == --ar) {
                        w = null;
                        window.location = an
                    }
                };
                if (at === null || at === undefined) {
                    at = ""
                }
                aj(aq, ap, at, an ? ao : null);
                al(aq, ap, at, an ? ao : null)
            }
        }
    }();

    function I(ai, aj, ak) {
        var ag, an, al, am, ah;
        if (t.win && t.ie) {
            an = "";
            for (ag in ai) {
                an += " " + ag + '="' + ai[ag] + '"'
            }
            al = "";
            for (ag in aj) {
                al += '<param name="' + ag + '" value="' + aj[ag] + '" />'
            }
            ak.outerHTML = "<object" + an + ">" + al + "</object>"
        } else {
            am = Y.createElement("object");
            for (ag in ai) {
                am.setAttribute(ag, ai[ag])
            }
            for (ag in aj) {
                ah = Y.createElement("param");
                ah.name = ag;
                ah.value = aj[ag];
                am.appendChild(ah)
            }
            ak.parentNode.replaceChild(am, ak)
        }
    }

    function o(ag) {
        if (typeof ag == "undefined") {
            return false
        }
        if (!ag.complete) {
            return false
        }
        if (typeof ag.naturalWidth != "undefined" && ag.naturalWidth == 0) {
            return false
        }
        return true
    }

    function H(aj) {
        var ah = false;
        for (var ai = 0; ai < l.length; ai++) {
            if (!l[ai]) {
                continue
            }
            var ag = Y.images[l[ai]];
            if (!o(ag)) {
                ah = true
            } else {
                l[ai] = null
            }
        }
        if (ah) {
            setTimeout(arguments.callee, 100)
        } else {
            setTimeout(function () {
                d(aj)
            }, 100)
        }
    }

    function d(aj) {
        var al = Y.getElementById(aj);
        if (!al) {
            al = Y.createElement("div");
            var ag = Y.body.lastChild;
            Y.body.insertBefore(al, ag.nextSibling)
        }
        var ak = x.baseDownloadUrl + "3.0/jws/";
        var ah = {
            id: aj,
            type: "application/x-java-applet",
            code: "JVMPreloader",
            width: 1,
            height: 1,
            name: "JVM Preloader"
        };
        var ai = {
            context: aj,
            codebase: ak,
            classloader_cache: false,
            scriptable: true,
            mayscript: true
        };
        I(ah, ai, al);
        jQuery("#" + aj).show()
    }

    function S(ah) {
        G = true;
        e(K, G);
        var aj = Y.getElementById(ah);
        var al = ah + "_applet_" + E;
        B[al] = {
            attributes: x.attributes,
            params: x.params,
            callback: x.callback,
            broken: x.broken
        };
        var an = B[al];
        var ak = {
            id: al,
            type: "application/x-java-applet",
            archive: x.baseDownloadUrl + "3.0/jws/UnityWebPlayer.jar",
            code: "UnityWebPlayer",
            width: 1,
            height: 1,
            name: "Unity Web Player"
        };
        if (t.win && t.ff) {
            ak.style = "visibility: hidden;"
        }
        var am = {
            context: al,
            jnlp_href: x.baseDownloadUrl + "3.0/jws/UnityWebPlayer.jnlp",
            classloader_cache: false,
            installer: m(),
            image: y + "installation/unitylogo.png",
            centerimage: true,
            boxborder: false,
            scriptable: true,
            mayscript: true
        };
        for (var ag in an.params) {
            if (ag == "src") {
                continue
            }
            if (an.params[ag] != Object.prototype[ag]) {
                am[ag] = an.params[ag];
                if (ag.toLowerCase() == "logoimage") {
                    am.image = an.params[ag]
                } else {
                    if (ag.toLowerCase() == "backgroundcolor") {
                        am.boxbgcolor = "#" + an.params[ag]
                    } else {
                        if (ag.toLowerCase() == "bordercolor") {
                            am.boxborder = true
                        } else {
                            if (ag.toLowerCase() == "textcolor") {
                                am.boxfgcolor = "#" + an.params[ag]
                            }
                        }
                    }
                }
            }
        }
        var ai = Y.createElement("div");
        aj.appendChild(ai);
        I(ak, am, ai);
        jQuery("#" + ah).show()
    }

    function X(ag) {
        setTimeout(function () {
            var ah = Y.getElementById(ag);
            if (ah) {
                ah.parentNode.removeChild(ah)
            }
        }, 0)
    }

    function g(ak) {
        var al = B[ak],
            aj = Y.getElementById(ak),
            ai;
        if (!aj) {
            return
        }
        aj.width = al.attributes.width || 600;
        aj.height = al.attributes.height || 450;
        var ah = aj.parentNode;
        var ag = ah.childNodes;
        for (var am = 0; am < ag.length; am++) {
            ai = ag[am];
            if (ai.nodeType == 1 && ai != aj) {
                ah.removeChild(ai)
            }
        }
    }

    function k(ai, ag, ah) {
        n("_javaInstallDoneCallback", ai, ag, ah);
        if (!ag) {
            D(F, A, ah)
        }
    }

    function ad() {
        ac.push(arguments);
        if (x.debugLevel > 0 && window.console && window.console.log) {
            console.log(Array.prototype.slice.call(arguments))
        }
    }

    function n() {
        ac.push(arguments);
        if (x.debugLevel > 1 && window.console && window.console.log) {
            console.log(Array.prototype.slice.call(arguments))
        }
    }

    function p(ag) {
        if (/^[-+]?[0-9]+$/.test(ag)) {
            ag += "px"
        }
        return ag
    }
    var Q = {
        getLogHistory: function () {
            return ac
        },
        getConfig: function () {
            return x
        },
        getPlatformInfo: function () {
            return t
        },
        initPlugin: function (ag, ah) {
            x.targetEl = ag;
            x.src = ah;
            n("ua:", t);
            this.detectUnity(this.handlePluginStatus)
        },
        detectUnity: function (at, ah) {
            var aq = this;
            var aj = L;
            var ak;
            W.plugins.refresh();
            if (t.clientBrand === "??" || t.clientPlatform === "???" || t.mobile) {
                aj = v
            } else {
                if (t.op && t.mac) {
                    aj = v;
                    ak = "OPERA-MAC"
                } else {
                    if (typeof W.plugins != "undefined" && W.plugins[x.pluginName] && typeof W.mimeTypes != "undefined" && W.mimeTypes[x.pluginMimeType] && W.mimeTypes[x.pluginMimeType].enabledPlugin) {
                        aj = U;
                        if (t.sf && /Mac OS X 10_6/.test(W.appVersion)) {
                            ae(function (au) {
                                if (!au || !au.plugin) {
                                    aj = b;
                                    ak = "OSX10.6-SFx64"
                                }
                                D(aj, P, ak);
                                at.call(aq, aj, au)
                            }, ah);
                            return
                        } else {
                            if (t.mac && t.ch) {
                                ae(function (au) {
                                    if (au && (N(au.plugin) <= N("2.6.1f3"))) {
                                        aj = b;
                                        ak = "OSX-CH-U<=2.6.1f3"
                                    }
                                    D(aj, P, ak);
                                    at.call(aq, aj, au)
                                }, ah);
                                return
                            } else {
                                if (ah) {
                                    ae(function (au) {
                                        D(aj, P, ak);
                                        at.call(aq, aj, au)
                                    }, ah);
                                    return
                                }
                            }
                        }
                    } else {
                        if (t.ie) {
                            var ai = false;
                            try {
                                if (ActiveXObject.prototype != null) {
                                    ai = true
                                }
                            } catch (am) { }
                            if (!ai) {
                                aj = v;
                                ak = "ActiveXFailed"
                            } else {
                                aj = L;
                                try {
                                    var ar = new ActiveXObject("UnityWebPlayer.UnityWebPlayer.1");
                                    var ag = ar.GetPluginVersion();
                                    if (ah) {
                                        var an = {};
                                        for (var ap = 0; ap < ah.length; ++ap) {
                                            an[ah[ap]] = ar.GetUnityVersion(ah[ap])
                                        }
                                        an.plugin = ag
                                    }
                                    aj = U;
                                    if (ag == "2.5.0f5") {
                                        var ao = /Windows NT \d+\.\d+/.exec(W.userAgent);
                                        if (ao && ao.length > 0) {
                                            var al = parseFloat(ao[0].split(" ")[2]);
                                            if (al >= 6) {
                                                aj = b;
                                                ak = "WIN-U2.5.0f5"
                                            }
                                        }
                                    }
                                } catch (am) { }
                            }
                        }
                    }
                }
            }
            D(aj, P, ak);
            at.call(aq, aj, an)
        },
        handlePluginStatus: function (ai, ag) {
            var ah = x.targetEl;
            var ak = jQuery(ah);
            switch (ai) {
                case U:
                    this.notifyProgress(ak);
                    this.embedPlugin(ak, x.callback);
                    break;
                case L:
                    this.notifyProgress(ak);
                    var aj = this;
                    var al = (x.debugLevel === 0) ? 1000 : 8000;
                    setTimeout(function () {
                        x.targetEl = ah;
                        aj.detectUnity(aj.handlePluginStatus)
                    }, al);
                    break;
                case b:
                    this.notifyProgress(ak);
                    break;
                case v:
                    this.notifyProgress(ak);
                    break
            }
        },
        getPluginURL: function () {
            var ag = "http://unity3d.com/webplayer/";
            if (t.win) {
                ag = x.baseDownloadUrl + c()
            } else {
                if (W.platform == "MacIntel") {
                    ag = x.baseDownloadUrl + (x.fullInstall ? "webplayer-i386.dmg" : "webplayer-mini.dmg");
                    if (x.referrer !== null) {
                        ag += "?referrer=" + x.referrer
                    }
                } else {
                    if (W.platform == "MacPPC") {
                        ag = x.baseDownloadUrl + (x.fullInstall ? "webplayer-ppc.dmg" : "webplayer-mini.dmg");
                        if (x.referrer !== null) {
                            ag += "?referrer=" + x.referrer
                        }
                    }
                }
            }
            return ag
        },
        getClickOnceURL: function () {
            return x.baseDownloadUrl + "3.0/co/UnityWebPlayer.application?installer=" + encodeURIComponent(x.baseDownloadUrl + c())
        },
        embedPlugin: function (aj, ar) {
            aj = jQuery(aj).empty();
            var ap = x.src;
            var ah = x.width || "100%";
            var am = x.height || "100%";
            var aq = this;
            if (t.win && t.ie) {
                var ai = "";
                for (var ag in x.attributes) {
                    if (x.attributes[ag] != Object.prototype[ag]) {
                        if (ag.toLowerCase() == "styleclass") {
                            ai += ' class="' + x.attributes[ag] + '"'
                        } else {
                            if (ag.toLowerCase() != "classid") {
                                ai += " " + ag + '="' + x.attributes[ag] + '"'
                            }
                        }
                    }
                }
                var al = "";
                al += '<param name="src" value="' + ap + '" />';
                al += '<param name="firstFrameCallback" value="UnityObject2.instances[' + E + '].firstFrameCallback();" />';
                for (var ag in x.params) {
                    if (x.params[ag] != Object.prototype[ag]) {
                        if (ag.toLowerCase() != "classid") {
                            al += '<param name="' + ag + '" value="' + x.params[ag] + '" />'
                        }
                    }
                }
                var ao = '<object classid="clsid:444785F1-DE89-4295-863A-D46C3A781394" style="display: block; width: ' + p(ah) + "; height: " + p(am) + ';"' + ai + ">" + al + "</object>";
                var an = jQuery(ao);
                aj.append(an);
                h.push(aj.attr("id"));
                R = an[0]
            } else {
                var ak = jQuery("<embed/>").attr({
                    src: ap,
                    type: x.pluginMimeType,
                    width: ah,
                    height: am,
                    firstFrameCallback: "UnityObject2.instances[" + E + "].firstFrameCallback();"
                }).attr(x.attributes).attr(x.params).css({
                    display: "block",
                    width: p(ah),
                    height: p(am)
                }).appendTo(aj);
                R = ak[0]
            }
            if (!t.sf || !t.mac) {
                setTimeout(function () {
                    R.focus()
                }, 100)
            }
            if (ar) {
                ar()
            }
        },
        getBestInstallMethod: function () {
            var ag = "Manual";
            if (t.x64) {
                return ag
            }
            if (x.enableJava && t.java && G === false) {
                ag = "JavaInstall"
            } else {
                if (x.enableClickOnce && t.co && u === false) {
                    ag = "ClickOnceIE"
                }
            }
            return ag
        },
        installPlugin: function (ah) {
            if (ah == null || ah == undefined) {
                ah = this.getBestInstallMethod()
            }
            var ag = null;
            switch (ah) {
                case "JavaInstall":
                    this.doJavaInstall(x.targetEl.id);
                    break;
                case "ClickOnceIE":
                    u = true;
                    e(r, u);
                    var ai = jQuery("<iframe src='" + this.getClickOnceURL() + "' style='display:none;' />");
                    jQuery(x.targetEl).append(ai);
                    break;
                default:
                case "Manual":
                    var ai = jQuery("<iframe src='" + this.getPluginURL() + "' style='display:none;' />");
                    jQuery(x.targetEl).append(ai);
                    break
            }
            P = ah;
            j.send(z, ah, null, null)
        },
        trigger: function (ah, ag) {
            if (ag) {
                n('trigger("' + ah + '")', ag)
            } else {
                n('trigger("' + ah + '")')
            }
            jQuery(document).trigger(ah, ag)
        },
        notifyProgress: function (ag) {
            if (typeof aa !== "undefined" && typeof aa === "function") {
                var ah = {
                    ua: t,
                    pluginStatus: T,
                    bestMethod: null,
                    lastType: P,
                    targetEl: x.targetEl,
                    unityObj: this
                };
                if (T === L) {
                    ah.bestMethod = this.getBestInstallMethod()
                }
                if (f !== T) {
                    f = T;
                    aa(ah)
                }
            }
        },
        observeProgress: function (ag) {
            aa = ag
        },
        firstFrameCallback: function () {
            n("*** firstFrameCallback (" + E + ") ***");
            T = Z;
            this.notifyProgress();
            if (M === true) {
                j.send(T, P)
            }
        },
        setPluginStatus: function (ai, ah, aj, ag) {
            D(ai, ah, aj, ag)
        },
        doJavaInstall: function (ag) {
            S(ag)
        },
        jvmPreloaded: function (ag) {
            X(ag)
        },
        appletStarted: function (ag) {
            g(ag)
        },
        javaInstallDoneCallback: function (ai, ag, ah) {
            k(ai, ag, ah)
        },
        getUnity: function () {
            return R
        }
    };
    E = UnityObject2.instances.length;
    UnityObject2.instances.push(Q);
    return Q
};
UnityObject2.instances = [];;

function postData()
{
	var actionUrl = arguments[0];
	var method = "post";
	var mapForm = $('<form id="mapform" action="' + actionUrl + '" method="' + method.toLowerCase() + '"></form>');
	for (var i = 1; i < arguments.length; i+=2) {
		var paramKey = arguments[i];
		var paramVal = arguments[i+1];
		mapForm.append('<input type="hidden" name="' + paramKey + '" id="' + paramKey + '" value="' + paramVal + '" />');
	}
	$('body').append(mapForm);
	mapForm.submit();
};

function measureText(words, size, family)
{
	// This global variable is used to cache repeated calls with the same arguments
	var str = words + ':' + family + ':' + size;
	if (typeof(__measuretext_cache__) == 'object' && __measuretext_cache__[str]) {
		return __measuretext_cache__[str];
	}

	var div = document.createElement('DIV');
		div.innerHTML = words;
		div.style.position = 'absolute';
		div.style.top = '-100px';
		div.style.left = '-100px';
		div.style.fontFamily = family;
		div.style.fontSize = size + 'px';
	document.body.appendChild(div);
	
	var size = [div.offsetWidth, div.offsetHeight];

	document.body.removeChild(div);
	
	// Add the sizes to the cache as adding DOM elements is costly and can cause slow downs
	if (typeof(__measuretext_cache__) != 'object') {
		__measuretext_cache__ = [];
	}
	__measuretext_cache__[str] = size;
	
	return size;
};

function getTexture(id, words, size, family){
	var measurement = measureText(words, size, family);
	
	var canvas = document.createElement('canvas');
	canvas.width = measurement[0];
	canvas.height = measurement[1];

	var context = canvas.getContext('2d');
	context.font = size + 'px ' + family;
	context.textAlign = 'start';
	context.textBaseline= 'top'; 
	context.fillStyle = 'black';
	context.fillText(words, 0, 0);
		
	var imgData = canvas.toDataURL();
	//console.log(imgData);
	u.getUnity().SendMessage("Main Script", "OnTexture", id+","+imgData);
};

function getUnityRunTimeParam(){
	u.getUnity().SendMessage("Main Script", "OnGetUnityRunTimeParam", unityRunTimeParam);
};

//重新传值给Unity
function refreshUnityRunTimeParam(data) {
    u.getUnity().SendMessage("Main Script", "OnGetUnityRunTimeParam", data);
}

function forceComfirmAddress(jsonResult) {
    u.getUnity().SendMessage("Main Script", "ForceComfirmAddress", jsonResult);
}

function cancelFullScreen(){
	u.getUnity().SendMessage("Main Script", "OnCancelFullScreen", "");
};
function changeBackground(data) {
    document.getElementsByClassName("unity_box")[0].style["background-image"] = "url('data:image/png;base64," + data + "')";
}

function removeBackgroud() {
    document.getElementsByClassName("unity_box")[0].style["background-image"] = "url('')";
}

function setUnitySize(width, height) {
   
    var unity = u.getUnity();
	var style = unity.style;
	unity.width = width;
	unity.height = height;
	style.width = width;
	style.height = height;
	var parentContainer = jQuery("#unityPlayer")[0];
	parentContainer.style.width = width;
	parentContainer.style.height = height;
}

function hideUnity(){
    cancelFullScreen();
    setTimeout("setUnitySize(\"1px\",\"1px\")",50);
}
  
function showUnity() {
	setUnitySize("100%", "100%");
}

function custom_alert(output_msg, title_msg, callback)
{
    $("<div></div>").html(output_msg).dialog({
        title: title_msg,
        resizable: false,
        modal: true,
        buttons: {
            "确定": function() 
            {
				if (callback != null){
					callback();
				}
				
                $( this ).dialog( "close" );
            }
        }
    });
}

//unity登陆框
function showLoginBox() {
    var $divLogin = $("#loginAndRegister");
    hideUnity();
    $divLogin.show(80).siblings().hide();
    $divLogin.find(".login_form").show(80);
    $divLogin.find(".register_form").hide();
}
//楼盘搜索框
function showApplySearchBox() {debugger;
alert();
    var $newApplyFirst = $("#new_apply div.new_apply_first");
    $newApplyFirst.find("input[class=select_text]").val("");
    $newApplyFirst.find("#mq").val("");
    $newApplyFirst.find("div.none_record").find(">p").hide();
    $('#new_apply').find("div.popup_box").hide();
    var defaultId = $("input[name=defaultApartment]").val();
    if (!IsNullOrEmpty(defaultId)) {
        console.log(defaultId);
        $('#new_apply').find("div.return_btn").show();
        $('#new_apply').find("div.return_btn").unbind("click").click(function () {
            $('#new_apply').find("div.return_btn").hide();
            showApartmentBox();
        });
    }
    $newApplyFirst.find("div.none_record").show().siblings().hide();
    $newApplyFirst.show(80).siblings().hide();
    createPCLinkage()
}
//地址联动
function createPCLinkage() {
    //省市地址库
    var cityJson = { "110000000000": [{ "_id": "54927294e84bdc1c8c4a2d85", "Name": "市辖区", "Code": "110100000000", "PName": "北京市", "PCode": "110000000000", "Layerable": 2, "Pinyin": "SXQ" }, { "_id": "54927294e84bdc1c8c4a45f7", "Name": "县", "Code": "110200000000", "PName": "北京市", "PCode": "110000000000", "Layerable": 2, "Pinyin": "X" }], "120000000000": [{ "_id": "549272a8e84bdc1c8c4a4972", "Name": "市辖区", "Code": "120100000000", "PName": "天津市", "PCode": "120000000000", "Layerable": 2, "Pinyin": "SXQ" }, { "_id": "549272a8e84bdc1c8c4a591f", "Name": "县", "Code": "120200000000", "PName": "天津市", "PCode": "120000000000", "Layerable": 2, "Pinyin": "X" }], "130000000000": [{ "_id": "549272abe84bdc1c8c4a6025", "Name": "石家庄市", "Code": "130100000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "SJZS" }, { "_id": "549272ace84bdc1c8c4a74ae", "Name": "唐山市", "Code": "130200000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "TSS" }, { "_id": "549272ace84bdc1c8c4a8c59", "Name": "秦皇岛市", "Code": "130300000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "QHDS" }, { "_id": "549272ace84bdc1c8c4a9662", "Name": "邯郸市", "Code": "130400000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "HDS" }, { "_id": "549272ade84bdc1c8c4aada2", "Name": "邢台市", "Code": "130500000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "XTS" }, { "_id": "549272ade84bdc1c8c4ac371", "Name": "保定市", "Code": "130600000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "BDS" }, { "_id": "549272aee84bdc1c8c4adea9", "Name": "张家口市", "Code": "130700000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "ZJKS" }, { "_id": "549272aee84bdc1c8c4af108", "Name": "承德市", "Code": "130800000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "CDS" }, { "_id": "549272aee84bdc1c8c4afc4f", "Name": "沧州市", "Code": "130900000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "CZS" }, { "_id": "549272afe84bdc1c8c4b14f1", "Name": "廊坊市", "Code": "131000000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "LFS" }, { "_id": "549272afe84bdc1c8c4b22b9", "Name": "衡水市", "Code": "131100000000", "PName": "河北省", "PCode": "130000000000", "Layerable": 2, "Pinyin": "HSS" }], "140000000000": [{ "_id": "549272b1e84bdc1c8c4b370a", "Name": "太原市", "Code": "140100000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "TYS" }, { "_id": "549272b1e84bdc1c8c4b3d7f", "Name": "大同市", "Code": "140200000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "DTS" }, { "_id": "549272b1e84bdc1c8c4b4708", "Name": "阳泉市", "Code": "140300000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "YQS" }, { "_id": "549272b1e84bdc1c8c4b4b6f", "Name": "长治市", "Code": "140400000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "CZS" }, { "_id": "549272b1e84bdc1c8c4b5a18", "Name": "晋城市", "Code": "140500000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "JCS" }, { "_id": "549272b1e84bdc1c8c4b63aa", "Name": "朔州市", "Code": "140600000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "SZS" }, { "_id": "549272b2e84bdc1c8c4b6b1a", "Name": "晋中市", "Code": "140700000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "JZS" }, { "_id": "549272b2e84bdc1c8c4b773d", "Name": "运城市", "Code": "140800000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "YCS" }, { "_id": "549272b2e84bdc1c8c4b8506", "Name": "忻州市", "Code": "140900000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "XZS" }, { "_id": "549272b2e84bdc1c8c4b9941", "Name": "临汾市", "Code": "141000000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "LFS" }, { "_id": "549272b3e84bdc1c8c4ba630", "Name": "吕梁市", "Code": "141100000000", "PName": "山西省", "PCode": "140000000000", "Layerable": 2, "Pinyin": "LLS" }], "150000000000": [{ "_id": "549272b4e84bdc1c8c4bb390", "Name": "呼和浩特市", "Code": "150100000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "HHHTS" }, { "_id": "549272b4e84bdc1c8c4bb8e8", "Name": "包头市", "Code": "150200000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "BTS" }, { "_id": "549272b4e84bdc1c8c4bbce8", "Name": "乌海市", "Code": "150300000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "WHS" }, { "_id": "549272b4e84bdc1c8c4bbd5d", "Name": "赤峰市", "Code": "150400000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "CFS" }, { "_id": "549272b4e84bdc1c8c4bc751", "Name": "通辽市", "Code": "150500000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "TLS" }, { "_id": "549272b5e84bdc1c8c4bd1c3", "Name": "鄂尔多斯市", "Code": "150600000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "EEDSS" }, { "_id": "549272b5e84bdc1c8c4bd602", "Name": "呼伦贝尔市", "Code": "150700000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "HLBES" }, { "_id": "549272b5e84bdc1c8c4bdc76", "Name": "巴彦淖尔市", "Code": "150800000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "BYNES" }, { "_id": "549272b5e84bdc1c8c4be086", "Name": "乌兰察布市", "Code": "150900000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "WLCBS" }, { "_id": "549272b5e84bdc1c8c4be6fe", "Name": "兴安盟", "Code": "152200000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "XAM" }, { "_id": "549272b6e84bdc1c8c4bebbd", "Name": "锡林郭勒盟", "Code": "152500000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "XLGLM" }, { "_id": "549272b6e84bdc1c8c4bf05d", "Name": "阿拉善盟", "Code": "152900000000", "PName": "内蒙古自治区", "PCode": "150000000000", "Layerable": 2, "Pinyin": "ALSM" }], "210000000000": [{ "_id": "549272b7e84bdc1c8c4bf181", "Name": "沈阳市", "Code": "210100000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "SYS" }, { "_id": "549272b7e84bdc1c8c4bfc01", "Name": "大连市", "Code": "210200000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "DLS" }, { "_id": "549272b7e84bdc1c8c4c0309", "Name": "鞍山市", "Code": "210300000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "ASS" }, { "_id": "549272b7e84bdc1c8c4c0813", "Name": "抚顺市", "Code": "210400000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "FSS" }, { "_id": "549272b8e84bdc1c8c4c0c3c", "Name": "本溪市", "Code": "210500000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "BXS" }, { "_id": "549272b8e84bdc1c8c4c0e8c", "Name": "丹东市", "Code": "210600000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "DDS" }, { "_id": "549272b8e84bdc1c8c4c124a", "Name": "锦州市", "Code": "210700000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "JZS" }, { "_id": "549272b8e84bdc1c8c4c183b", "Name": "营口市", "Code": "210800000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "YKS" }, { "_id": "549272b8e84bdc1c8c4c1bbb", "Name": "阜新市", "Code": "210900000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "FXS" }, { "_id": "549272b8e84bdc1c8c4c1f4f", "Name": "辽阳市", "Code": "211000000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "LYS" }, { "_id": "549272b9e84bdc1c8c4c2239", "Name": "盘锦市", "Code": "211100000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "PJS" }, { "_id": "549272b9e84bdc1c8c4c2475", "Name": "铁岭市", "Code": "211200000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "TLS" }, { "_id": "549272b9e84bdc1c8c4c2a7a", "Name": "朝阳市", "Code": "211300000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "CYS" }, { "_id": "549272b9e84bdc1c8c4c3145", "Name": "葫芦岛市", "Code": "211400000000", "PName": "辽宁省", "PCode": "210000000000", "Layerable": 2, "Pinyin": "HLDS" }], "220000000000": [{ "_id": "549272bae84bdc1c8c4c36e7", "Name": "长春市", "Code": "220100000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "CCS" }, { "_id": "549272bae84bdc1c8c4c3fdd", "Name": "吉林市", "Code": "220200000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "JLS" }, { "_id": "549272bae84bdc1c8c4c473b", "Name": "四平市", "Code": "220300000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "SPS" }, { "_id": "549272bbe84bdc1c8c4c4d33", "Name": "辽源市", "Code": "220400000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "LYS" }, { "_id": "549272bbe84bdc1c8c4c4f9e", "Name": "通化市", "Code": "220500000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "THS" }, { "_id": "549272bbe84bdc1c8c4c5496", "Name": "白山市", "Code": "220600000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "BSS" }, { "_id": "549272bbe84bdc1c8c4c578f", "Name": "松原市", "Code": "220700000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "SYS" }, { "_id": "549272bbe84bdc1c8c4c5d27", "Name": "白城市", "Code": "220800000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "BCS" }, { "_id": "549272bbe84bdc1c8c4c61f5", "Name": "延边朝鲜族自治州", "Code": "222400000000", "PName": "吉林省", "PCode": "220000000000", "Layerable": 2, "Pinyin": "YBCXZZZZ" }], "230000000000": [{ "_id": "549272bce84bdc1c8c4c6885", "Name": "哈尔滨市", "Code": "230100000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "HEBS" }, { "_id": "549272bde84bdc1c8c4c75d6", "Name": "齐齐哈尔市", "Code": "230200000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "QQHES" }, { "_id": "549272bde84bdc1c8c4c7d31", "Name": "鸡西市", "Code": "230300000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "JXS" }, { "_id": "549272bde84bdc1c8c4c80c4", "Name": "鹤岗市", "Code": "230400000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "HGS" }, { "_id": "549272bde84bdc1c8c4c830b", "Name": "双鸭山市", "Code": "230500000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "SYSS" }, { "_id": "549272bee84bdc1c8c4c8690", "Name": "大庆市", "Code": "230600000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "DQS" }, { "_id": "549272bee84bdc1c8c4c8a0a", "Name": "伊春市", "Code": "230700000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "YCS" }, { "_id": "549272bee84bdc1c8c4c8d75", "Name": "佳木斯市", "Code": "230800000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "JMSS" }, { "_id": "549272bee84bdc1c8c4c93c6", "Name": "七台河市", "Code": "230900000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "QTHS" }, { "_id": "549272bee84bdc1c8c4c9574", "Name": "牡丹江市", "Code": "231000000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "MDJS" }, { "_id": "549272bfe84bdc1c8c4c9b51", "Name": "黑河市", "Code": "231100000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "HHS" }, { "_id": "549272bfe84bdc1c8c4c9fac", "Name": "绥化市", "Code": "231200000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "SHS" }, { "_id": "549272bfe84bdc1c8c4ca71a", "Name": "大兴安岭地区", "Code": "232700000000", "PName": "黑龙江省", "PCode": "230000000000", "Layerable": 2, "Pinyin": "DXALDQ" }], "310000000000": [{ "_id": "549272c0e84bdc1c8c4ca7a5", "Name": "市辖区", "Code": "310100000000", "PName": "上海市", "PCode": "310000000000", "Layerable": 2, "Pinyin": "SXQ" }, { "_id": "549272c0e84bdc1c8c4cbd40", "Name": "县", "Code": "310200000000", "PName": "上海市", "PCode": "310000000000", "Layerable": 2, "Pinyin": "X" }], "320000000000": [{ "_id": "549272c1e84bdc1c8c4cbeae", "Name": "南京市", "Code": "320100000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "NJS" }, { "_id": "549272c1e84bdc1c8c4cc480", "Name": "无锡市", "Code": "320200000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "WXS" }, { "_id": "549272c1e84bdc1c8c4cc9d1", "Name": "徐州市", "Code": "320300000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "XZS" }, { "_id": "549272c2e84bdc1c8c4cd563", "Name": "常州市", "Code": "320400000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "CZS" }, { "_id": "549272c2e84bdc1c8c4cd9f7", "Name": "苏州市", "Code": "320500000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "SZS" }, { "_id": "549272c2e84bdc1c8c4ce296", "Name": "南通市", "Code": "320600000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "NTS" }, { "_id": "549272c2e84bdc1c8c4ceae2", "Name": "连云港市", "Code": "320700000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "LYGS" }, { "_id": "549272c2e84bdc1c8c4cf206", "Name": "淮安市", "Code": "320800000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "HAS" }, { "_id": "549272c2e84bdc1c8c4cf955", "Name": "盐城市", "Code": "320900000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "YCS" }, { "_id": "549272c3e84bdc1c8c4d03f5", "Name": "扬州市", "Code": "321000000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "YZS" }, { "_id": "549272c3e84bdc1c8c4d09dc", "Name": "镇江市", "Code": "321100000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "ZJS" }, { "_id": "549272c3e84bdc1c8c4d0d2b", "Name": "泰州市", "Code": "321200000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "TZS" }, { "_id": "549272c3e84bdc1c8c4d1504", "Name": "宿迁市", "Code": "321300000000", "PName": "江苏省", "PCode": "320000000000", "Layerable": 2, "Pinyin": "SQS" }], "330000000000": [{ "_id": "549272c5e84bdc1c8c4d1b5b", "Name": "杭州市", "Code": "330100000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "HZS" }, { "_id": "549272c5e84bdc1c8c4d284d", "Name": "宁波市", "Code": "330200000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "NBS" }, { "_id": "549272c5e84bdc1c8c4d35b3", "Name": "温州市", "Code": "330300000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "WZS" }, { "_id": "549272c6e84bdc1c8c4d4d19", "Name": "嘉兴市", "Code": "330400000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "JXS" }, { "_id": "549272c6e84bdc1c8c4d5207", "Name": "湖州市", "Code": "330500000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "HZS" }, { "_id": "549272c6e84bdc1c8c4d5739", "Name": "绍兴市", "Code": "330600000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "SXS" }, { "_id": "549272c6e84bdc1c8c4d6226", "Name": "金华市", "Code": "330700000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "JHS" }, { "_id": "549272c6e84bdc1c8c4d76f4", "Name": "衢州市", "Code": "330800000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "QZS" }, { "_id": "549272c7e84bdc1c8c4d7e2c", "Name": "舟山市", "Code": "330900000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "ZSS" }, { "_id": "549272c7e84bdc1c8c4d8012", "Name": "台州市", "Code": "331000000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "TZS" }, { "_id": "549272c7e84bdc1c8c4d95a4", "Name": "丽水市", "Code": "331100000000", "PName": "浙江省", "PCode": "330000000000", "Layerable": 2, "Pinyin": "LSS" }], "340000000000": [{ "_id": "549272c8e84bdc1c8c4da1fe", "Name": "合肥市", "Code": "340100000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "HFS" }, { "_id": "549272c8e84bdc1c8c4da9a1", "Name": "芜湖市", "Code": "340200000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "WHS" }, { "_id": "549272c8e84bdc1c8c4dadcd", "Name": "蚌埠市", "Code": "340300000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "BBS" }, { "_id": "549272c9e84bdc1c8c4db272", "Name": "淮南市", "Code": "340400000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "HNS" }, { "_id": "549272c9e84bdc1c8c4db5eb", "Name": "马鞍山市", "Code": "340500000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "MASS" }, { "_id": "549272c9e84bdc1c8c4db868", "Name": "淮北市", "Code": "340600000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "HBS" }, { "_id": "549272c9e84bdc1c8c4dba70", "Name": "铜陵市", "Code": "340700000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "TLS" }, { "_id": "549272c9e84bdc1c8c4dbb6a", "Name": "安庆市", "Code": "340800000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "AQS" }, { "_id": "549272c9e84bdc1c8c4dc372", "Name": "黄山市", "Code": "341000000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "HSS" }, { "_id": "549272c9e84bdc1c8c4dc700", "Name": "滁州市", "Code": "341100000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "CZS" }, { "_id": "549272cae84bdc1c8c4dcc9c", "Name": "阜阳市", "Code": "341200000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "FYS" }, { "_id": "549272cae84bdc1c8c4dd503", "Name": "宿州市", "Code": "341300000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "SZS" }, { "_id": "549272cae84bdc1c8c4ddaeb", "Name": "六安市", "Code": "341500000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "LAS" }, { "_id": "549272cae84bdc1c8c4de46b", "Name": "亳州市", "Code": "341600000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "BZS" }, { "_id": "549272cbe84bdc1c8c4dea1f", "Name": "池州市", "Code": "341700000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "CZS" }, { "_id": "549272cbe84bdc1c8c4ded1a", "Name": "宣城市", "Code": "341800000000", "PName": "安徽省", "PCode": "340000000000", "Layerable": 2, "Pinyin": "XCS" }], "350000000000": [{ "_id": "549272cce84bdc1c8c4df101", "Name": "福州市", "Code": "350100000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "FZS" }, { "_id": "549272cce84bdc1c8c4dfd28", "Name": "厦门市", "Code": "350200000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "XMS" }, { "_id": "549272cce84bdc1c8c4dff54", "Name": "莆田市", "Code": "350300000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "PTS" }, { "_id": "549272cce84bdc1c8c4e0364", "Name": "三明市", "Code": "350400000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "SMS" }, { "_id": "549272cce84bdc1c8c4e0b8c", "Name": "泉州市", "Code": "350500000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "QZS" }, { "_id": "549272cde84bdc1c8c4e160e", "Name": "漳州市", "Code": "350600000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "ZZS" }, { "_id": "549272cde84bdc1c8c4e1e96", "Name": "南平市", "Code": "350700000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "NPS" }, { "_id": "549272cde84bdc1c8c4e2680", "Name": "龙岩市", "Code": "350800000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "LYS" }, { "_id": "549272cde84bdc1c8c4e2e8c", "Name": "宁德市", "Code": "350900000000", "PName": "福建省", "PCode": "350000000000", "Layerable": 2, "Pinyin": "NDS" }], "360000000000": [{ "_id": "549272cfe84bdc1c8c4e3821", "Name": "南昌市", "Code": "360100000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "NCS" }, { "_id": "549272cfe84bdc1c8c4e403b", "Name": "景德镇市", "Code": "360200000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "JDZS" }, { "_id": "549272cfe84bdc1c8c4e435b", "Name": "萍乡市", "Code": "360300000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "PXS" }, { "_id": "549272cfe84bdc1c8c4e46a3", "Name": "九江市", "Code": "360400000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "JJS" }, { "_id": "549272cfe84bdc1c8c4e5098", "Name": "新余市", "Code": "360500000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "XYS" }, { "_id": "549272cfe84bdc1c8c4e52bb", "Name": "鹰潭市", "Code": "360600000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "YTS" }, { "_id": "549272d0e84bdc1c8c4e54ec", "Name": "赣州市", "Code": "360700000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "GZS" }, { "_id": "549272d0e84bdc1c8c4e6533", "Name": "吉安市", "Code": "360800000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "JAS" }, { "_id": "549272d0e84bdc1c8c4e717d", "Name": "宜春市", "Code": "360900000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "YCS" }, { "_id": "549272d1e84bdc1c8c4e7ce0", "Name": "抚州市", "Code": "361000000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "FZS" }, { "_id": "549272d1e84bdc1c8c4e85b5", "Name": "上饶市", "Code": "361100000000", "PName": "江西省", "PCode": "360000000000", "Layerable": 2, "Pinyin": "SRS" }], "370000000000": [{ "_id": "549272d4e84bdc1c8c4e915b", "Name": "济南市", "Code": "370100000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "JNS" }, { "_id": "549272d5e84bdc1c8c4ea603", "Name": "青岛市", "Code": "370200000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "QDS" }, { "_id": "549272d5e84bdc1c8c4ec0c8", "Name": "淄博市", "Code": "370300000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "ZBS" }, { "_id": "549272d5e84bdc1c8c4ecf1a", "Name": "枣庄市", "Code": "370400000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "ZZS" }, { "_id": "549272d5e84bdc1c8c4ed921", "Name": "东营市", "Code": "370500000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "DYS" }, { "_id": "549272d5e84bdc1c8c4ee0f9", "Name": "烟台市", "Code": "370600000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "YTS" }, { "_id": "549272d5e84bdc1c8c4efbe0", "Name": "潍坊市", "Code": "370700000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "WFS" }, { "_id": "549272d6e84bdc1c8c4f17f7", "Name": "济宁市", "Code": "370800000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "JNS" }, { "_id": "549272d6e84bdc1c8c4f3235", "Name": "泰安市", "Code": "370900000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "TAS" }, { "_id": "549272d6e84bdc1c8c4f4147", "Name": "威海市", "Code": "371000000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "WHS" }, { "_id": "549272d6e84bdc1c8c4f4cf7", "Name": "日照市", "Code": "371100000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "RZS" }, { "_id": "549272d6e84bdc1c8c4f58fd", "Name": "莱芜市", "Code": "371200000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "LWS" }, { "_id": "549272d6e84bdc1c8c4f5d43", "Name": "临沂市", "Code": "371300000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "LYS" }, { "_id": "549272d7e84bdc1c8c4f79d9", "Name": "德州市", "Code": "371400000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "DZS" }, { "_id": "549272d7e84bdc1c8c4f8eb7", "Name": "聊城市", "Code": "371500000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "LCS" }, { "_id": "549272d8e84bdc1c8c4fa648", "Name": "滨州市", "Code": "371600000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "BZS" }, { "_id": "549272d8e84bdc1c8c4fbb65", "Name": "菏泽市", "Code": "371700000000", "PName": "山东省", "PCode": "370000000000", "Layerable": 2, "Pinyin": "HZS" }], "410000000000": [{ "_id": "549272dbe84bdc1c8c4fd3dc", "Name": "郑州市", "Code": "410100000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "ZZS" }, { "_id": "549272dbe84bdc1c8c4fe041", "Name": "开封市", "Code": "410200000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "KFS" }, { "_id": "549272dbe84bdc1c8c4feab4", "Name": "洛阳市", "Code": "410300000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "LYS" }, { "_id": "549272dbe84bdc1c8c4ff814", "Name": "平顶山市", "Code": "410400000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "PDSS" }, { "_id": "549272dce84bdc1c8c500395", "Name": "安阳市", "Code": "410500000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "AYS" }, { "_id": "549272dce84bdc1c8c5011de", "Name": "鹤壁市", "Code": "410600000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "HBS" }, { "_id": "549272dce84bdc1c8c5015e1", "Name": "新乡市", "Code": "410700000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "XXS" }, { "_id": "549272dce84bdc1c8c502557", "Name": "焦作市", "Code": "410800000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "JZS" }, { "_id": "549272dce84bdc1c8c502d97", "Name": "濮阳市", "Code": "410900000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "PYS" }, { "_id": "549272dde84bdc1c8c503a20", "Name": "许昌市", "Code": "411000000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "XCS" }, { "_id": "549272dde84bdc1c8c5043ff", "Name": "漯河市", "Code": "411100000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "LHS" }, { "_id": "549272dde84bdc1c8c50496b", "Name": "三门峡市", "Code": "411200000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "SMXS" }, { "_id": "549272dde84bdc1c8c504f80", "Name": "南阳市", "Code": "411300000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "NYS" }, { "_id": "549272dde84bdc1c8c5063df", "Name": "商丘市", "Code": "411400000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "SQS" }, { "_id": "549272dee84bdc1c8c5077be", "Name": "信阳市", "Code": "411500000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "XYS" }, { "_id": "549272dee84bdc1c8c5085dd", "Name": "周口市", "Code": "411600000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "ZKS" }, { "_id": "549272dee84bdc1c8c509a73", "Name": "驻马店市", "Code": "411700000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "ZMDS" }, { "_id": "549272dfe84bdc1c8c50a699", "Name": "省直辖县级行政区划", "Code": "419000000000", "PName": "河南省", "PCode": "410000000000", "Layerable": 2, "Pinyin": "SZXXJXZQH" }], "420000000000": [{ "_id": "549272e0e84bdc1c8c50a8d4", "Name": "武汉市", "Code": "420100000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "WHS" }, { "_id": "549272e1e84bdc1c8c50b6ed", "Name": "黄石市", "Code": "420200000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "HSS" }, { "_id": "549272e1e84bdc1c8c50baef", "Name": "十堰市", "Code": "420300000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "SYS" }, { "_id": "549272e1e84bdc1c8c50c3a7", "Name": "宜昌市", "Code": "420500000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "YCS" }, { "_id": "549272e1e84bdc1c8c50ca96", "Name": "襄阳市", "Code": "420600000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "XYS" }, { "_id": "549272e1e84bdc1c8c50d5eb", "Name": "鄂州市", "Code": "420700000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "EZS" }, { "_id": "549272e1e84bdc1c8c50d78a", "Name": "荆门市", "Code": "420800000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "JMS" }, { "_id": "549272e1e84bdc1c8c50de6d", "Name": "孝感市", "Code": "420900000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "XGS" }, { "_id": "549272e2e84bdc1c8c50eb12", "Name": "荆州市", "Code": "421000000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "JZS" }, { "_id": "549272e2e84bdc1c8c50f75f", "Name": "黄冈市", "Code": "421100000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "HGS" }, { "_id": "549272e2e84bdc1c8c5109f8", "Name": "咸宁市", "Code": "421200000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "XNS" }, { "_id": "549272e2e84bdc1c8c510e8c", "Name": "随州市", "Code": "421300000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "SZS" }, { "_id": "549272e2e84bdc1c8c5112ae", "Name": "恩施土家族苗族自治州", "Code": "422800000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "ESTJZMZZZZ" }, { "_id": "549272e3e84bdc1c8c511d74", "Name": "省直辖县级行政区划", "Code": "429000000000", "PName": "湖北省", "PCode": "420000000000", "Layerable": 2, "Pinyin": "SZXXJXZQH" }], "430000000000": [{ "_id": "549272e5e84bdc1c8c51263c", "Name": "长沙市", "Code": "430100000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "CSS" }, { "_id": "549272e5e84bdc1c8c512e61", "Name": "株洲市", "Code": "430200000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "ZZS" }, { "_id": "549272e5e84bdc1c8c513681", "Name": "湘潭市", "Code": "430300000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "XTS" }, { "_id": "549272e6e84bdc1c8c513e06", "Name": "衡阳市", "Code": "430400000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "HYS" }, { "_id": "549272e6e84bdc1c8c515423", "Name": "邵阳市", "Code": "430500000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "SYS" }, { "_id": "549272e6e84bdc1c8c516c51", "Name": "岳阳市", "Code": "430600000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "YYS" }, { "_id": "549272e7e84bdc1c8c517b22", "Name": "常德市", "Code": "430700000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "CDS" }, { "_id": "549272e7e84bdc1c8c518b08", "Name": "张家界市", "Code": "430800000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "ZJJS" }, { "_id": "549272e7e84bdc1c8c5191f5", "Name": "益阳市", "Code": "430900000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "YYS" }, { "_id": "549272e7e84bdc1c8c519a50", "Name": "郴州市", "Code": "431000000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "CZS" }, { "_id": "549272e8e84bdc1c8c51a7eb", "Name": "永州市", "Code": "431100000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "YZS" }, { "_id": "549272e8e84bdc1c8c51bdef", "Name": "怀化市", "Code": "431200000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "HHS" }, { "_id": "549272e9e84bdc1c8c51cf5a", "Name": "娄底市", "Code": "431300000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "LDS" }, { "_id": "549272e9e84bdc1c8c51dd7b", "Name": "湘西土家族苗族自治州", "Code": "433100000000", "PName": "湖南省", "PCode": "430000000000", "Layerable": 2, "Pinyin": "XXTJZMZZZZ" }], "440000000000": [{ "_id": "549272eae84bdc1c8c51e697", "Name": "广州市", "Code": "440100000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "GZS" }, { "_id": "549272eae84bdc1c8c51f1d2", "Name": "韶关市", "Code": "440200000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "SGS" }, { "_id": "549272ebe84bdc1c8c51f808", "Name": "深圳市", "Code": "440300000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "SZS" }, { "_id": "549272ebe84bdc1c8c51fb61", "Name": "珠海市", "Code": "440400000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "ZHS" }, { "_id": "549272ebe84bdc1c8c51fccb", "Name": "汕头市", "Code": "440500000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "STS" }, { "_id": "549272ebe84bdc1c8c520142", "Name": "佛山市", "Code": "440600000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "FSS" }, { "_id": "549272ebe84bdc1c8c520474", "Name": "江门市", "Code": "440700000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "JMS" }, { "_id": "549272ebe84bdc1c8c5209fe", "Name": "湛江市", "Code": "440800000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "ZJS" }, { "_id": "549272ebe84bdc1c8c5211c4", "Name": "茂名市", "Code": "440900000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "MMS" }, { "_id": "549272ece84bdc1c8c5219b1", "Name": "肇庆市", "Code": "441200000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "ZQS" }, { "_id": "549272ece84bdc1c8c522030", "Name": "惠州市", "Code": "441300000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "HZS" }, { "_id": "549272ece84bdc1c8c522589", "Name": "梅州市", "Code": "441400000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "MZS" }, { "_id": "549272ece84bdc1c8c522ece", "Name": "汕尾市", "Code": "441500000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "SWS" }, { "_id": "549272ece84bdc1c8c52328b", "Name": "河源市", "Code": "441600000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "HYS" }, { "_id": "549272ece84bdc1c8c523886", "Name": "阳江市", "Code": "441700000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "YJS" }, { "_id": "549272ece84bdc1c8c523c1f", "Name": "清远市", "Code": "441800000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "QYS" }, { "_id": "549272ede84bdc1c8c52413e", "Name": "东莞市", "Code": "441900000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "DGS" }, { "_id": "549272ede84bdc1c8c52413f", "Name": "中山市", "Code": "442000000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "ZSS" }, { "_id": "549272ede84bdc1c8c524140", "Name": "潮州市", "Code": "445100000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "CZS" }, { "_id": "549272ede84bdc1c8c524581", "Name": "揭阳市", "Code": "445200000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "JYS" }, { "_id": "549272ede84bdc1c8c524c5e", "Name": "云浮市", "Code": "445300000000", "PName": "广东省", "PCode": "440000000000", "Layerable": 2, "Pinyin": "YFS" }], "450000000000": [{ "_id": "549272eee84bdc1c8c52507d", "Name": "南宁市", "Code": "450100000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "NNS" }, { "_id": "549272eee84bdc1c8c525823", "Name": "柳州市", "Code": "450200000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "LZS" }, { "_id": "549272eee84bdc1c8c525d6e", "Name": "桂林市", "Code": "450300000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "GLS" }, { "_id": "549272efe84bdc1c8c52657b", "Name": "梧州市", "Code": "450400000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "WZS" }, { "_id": "549272efe84bdc1c8c5269b5", "Name": "北海市", "Code": "450500000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "BHS" }, { "_id": "549272efe84bdc1c8c526b8e", "Name": "防城港市", "Code": "450600000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "FCGS" }, { "_id": "549272efe84bdc1c8c526d16", "Name": "钦州市", "Code": "450700000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "QZS" }, { "_id": "549272efe84bdc1c8c527175", "Name": "贵港市", "Code": "450800000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "GGS" }, { "_id": "549272efe84bdc1c8c527646", "Name": "玉林市", "Code": "450900000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "YLS" }, { "_id": "549272efe84bdc1c8c527c8b", "Name": "百色市", "Code": "451000000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "BSS" }, { "_id": "549272efe84bdc1c8c528475", "Name": "贺州市", "Code": "451100000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "HZS" }, { "_id": "549272f0e84bdc1c8c528726", "Name": "河池市", "Code": "451200000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "HCS" }, { "_id": "549272f0e84bdc1c8c528e30", "Name": "来宾市", "Code": "451300000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "LBS" }, { "_id": "549272f0e84bdc1c8c529199", "Name": "崇左市", "Code": "451400000000", "PName": "广西壮族自治区", "PCode": "450000000000", "Layerable": 2, "Pinyin": "CZS" }], "460000000000": [{ "_id": "549272f0e84bdc1c8c529585", "Name": "海口市", "Code": "460100000000", "PName": "海南省", "PCode": "460000000000", "Layerable": 2, "Pinyin": "HKS" }, { "_id": "549272f0e84bdc1c8c52978a", "Name": "三亚市", "Code": "460200000000", "PName": "海南省", "PCode": "460000000000", "Layerable": 2, "Pinyin": "SYS" }, { "_id": "549272f0e84bdc1c8c52983f", "Name": "三沙市", "Code": "460300000000", "PName": "海南省", "PCode": "460000000000", "Layerable": 2, "Pinyin": "SSS" }, { "_id": "549272f0e84bdc1c8c529849", "Name": "省直辖县级行政区划", "Code": "469000000000", "PName": "海南省", "PCode": "460000000000", "Layerable": 2, "Pinyin": "SZXXJXZQH" }], "500000000000": [{ "_id": "549272f1e84bdc1c8c52a4fe", "Name": "市辖区", "Code": "500100000000", "PName": "重庆市", "PCode": "500000000000", "Layerable": 2, "Pinyin": "SXQ" }, { "_id": "549272f2e84bdc1c8c52bb10", "Name": "县", "Code": "500200000000", "PName": "重庆市", "PCode": "500000000000", "Layerable": 2, "Pinyin": "X" }], "510000000000": [{ "_id": "549272f6e84bdc1c8c52d443", "Name": "成都市", "Code": "510100000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "CDS" }, { "_id": "549272f6e84bdc1c8c52e2d8", "Name": "自贡市", "Code": "510300000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "ZGS" }, { "_id": "549272f6e84bdc1c8c52e8cc", "Name": "攀枝花市", "Code": "510400000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "PZHS" }, { "_id": "549272f6e84bdc1c8c52eaf4", "Name": "泸州市", "Code": "510500000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "LZS" }, { "_id": "549272f7e84bdc1c8c52f1e6", "Name": "德阳市", "Code": "510600000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "DYS" }, { "_id": "549272f7e84bdc1c8c52f972", "Name": "绵阳市", "Code": "510700000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "MYS" }, { "_id": "549272f7e84bdc1c8c530984", "Name": "广元市", "Code": "510800000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "GYS" }, { "_id": "549272f8e84bdc1c8c531569", "Name": "遂宁市", "Code": "510900000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "SNS" }, { "_id": "549272f8e84bdc1c8c531f20", "Name": "内江市", "Code": "511000000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "NJS" }, { "_id": "549272f8e84bdc1c8c5328be", "Name": "乐山市", "Code": "511100000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "LSS" }, { "_id": "549272f9e84bdc1c8c5332a5", "Name": "南充市", "Code": "511300000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "NCS" }, { "_id": "549272f9e84bdc1c8c534ada", "Name": "眉山市", "Code": "511400000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "MSS" }, { "_id": "549272fae84bdc1c8c5350a7", "Name": "宜宾市", "Code": "511500000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "YBS" }, { "_id": "549272fae84bdc1c8c535dd3", "Name": "广安市", "Code": "511600000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "GAS" }, { "_id": "549272fae84bdc1c8c536a34", "Name": "达州市", "Code": "511700000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "DZS" }, { "_id": "549272fbe84bdc1c8c5377ee", "Name": "雅安市", "Code": "511800000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "YAS" }, { "_id": "549272fbe84bdc1c8c537cc0", "Name": "巴中市", "Code": "511900000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "BZS" }, { "_id": "549272fbe84bdc1c8c5387d9", "Name": "资阳市", "Code": "512000000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "ZYS" }, { "_id": "549272fce84bdc1c8c539426", "Name": "阿坝藏族羌族自治州", "Code": "513200000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "ABCZQZZZZ" }, { "_id": "549272fce84bdc1c8c539aa2", "Name": "甘孜藏族自治州", "Code": "513300000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "GZCZZZZ" }, { "_id": "549272fce84bdc1c8c53a6e0", "Name": "凉山彝族自治州", "Code": "513400000000", "PName": "四川省", "PCode": "510000000000", "Layerable": 2, "Pinyin": "LSYZZZZ" }], "520000000000": [{ "_id": "549272fee84bdc1c8c53b871", "Name": "贵阳市", "Code": "520100000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "GYS" }, { "_id": "549272ffe84bdc1c8c53bf8c", "Name": "六盘水市", "Code": "520200000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "LPSS" }, { "_id": "549272ffe84bdc1c8c53c489", "Name": "遵义市", "Code": "520300000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "ZYS" }, { "_id": "549272ffe84bdc1c8c53cd6d", "Name": "安顺市", "Code": "520400000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "ASS" }, { "_id": "549272ffe84bdc1c8c53d56a", "Name": "毕节市", "Code": "520500000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "BJS" }, { "_id": "54927300e84bdc1c8c53e4f8", "Name": "铜仁市", "Code": "520600000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "TRS" }, { "_id": "54927300e84bdc1c8c53f114", "Name": "黔西南布依族苗族自治州", "Code": "522300000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "QXNBYZMZZZZ" }, { "_id": "54927300e84bdc1c8c53f665", "Name": "黔东南苗族侗族自治州", "Code": "522600000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "QDNMZDZZZZ" }, { "_id": "54927301e84bdc1c8c5404eb", "Name": "黔南布依族苗族自治州", "Code": "522700000000", "PName": "贵州省", "PCode": "520000000000", "Layerable": 2, "Pinyin": "QNBYZMZZZZ" }], "530000000000": [{ "_id": "54927302e84bdc1c8c540ce2", "Name": "昆明市", "Code": "530100000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "KMS" }, { "_id": "54927302e84bdc1c8c5413dc", "Name": "曲靖市", "Code": "530300000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "QJS" }, { "_id": "54927302e84bdc1c8c541aa0", "Name": "玉溪市", "Code": "530400000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "YXS" }, { "_id": "54927302e84bdc1c8c541db4", "Name": "保山市", "Code": "530500000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "BSS" }, { "_id": "54927303e84bdc1c8c54219a", "Name": "昭通市", "Code": "530600000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "ZTS" }, { "_id": "54927303e84bdc1c8c542763", "Name": "丽江市", "Code": "530700000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "LJS" }, { "_id": "54927303e84bdc1c8c542975", "Name": "普洱市", "Code": "530800000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "PES" }, { "_id": "54927303e84bdc1c8c542df6", "Name": "临沧市", "Code": "530900000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "LCS" }, { "_id": "54927303e84bdc1c8c543204", "Name": "楚雄彝族自治州", "Code": "532300000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "CXYZZZZ" }, { "_id": "54927303e84bdc1c8c5436c0", "Name": "红河哈尼族彝族自治州", "Code": "532500000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "HHHNZYZZZZ" }, { "_id": "54927304e84bdc1c8c543c79", "Name": "文山壮族苗族自治州", "Code": "532600000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "WSZZMZZZZ" }, { "_id": "54927304e84bdc1c8c5440b1", "Name": "西双版纳傣族自治州", "Code": "532800000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "XSBNDZZZZ" }, { "_id": "54927304e84bdc1c8c5441e7", "Name": "大理白族自治州", "Code": "532900000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "DLBZZZZ" }, { "_id": "54927304e84bdc1c8c5446dc", "Name": "德宏傣族景颇族自治州", "Code": "533100000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "DHDZJPZZZZ" }, { "_id": "54927304e84bdc1c8c54488d", "Name": "怒江傈僳族自治州", "Code": "533300000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "NJLSZZZZ" }, { "_id": "54927304e84bdc1c8c5449bd", "Name": "迪庆藏族自治州", "Code": "533400000000", "PName": "云南省", "PCode": "530000000000", "Layerable": 2, "Pinyin": "DQCZZZZ" }], "540000000000": [{ "_id": "54927305e84bdc1c8c544a9a", "Name": "拉萨市", "Code": "540100000000", "PName": "西藏自治区", "PCode": "540000000000", "Layerable": 2, "Pinyin": "LSS" }, { "_id": "54927305e84bdc1c8c544bf0", "Name": "昌都地区", "Code": "542100000000", "PName": "西藏自治区", "PCode": "540000000000", "Layerable": 2, "Pinyin": "CDDQ" }, { "_id": "54927305e84bdc1c8c5450fc", "Name": "山南地区", "Code": "542200000000", "PName": "西藏自治区", "PCode": "540000000000", "Layerable": 2, "Pinyin": "SNDQ" }, { "_id": "54927305e84bdc1c8c545385", "Name": "日喀则地区", "Code": "542300000000", "PName": "西藏自治区", "PCode": "540000000000", "Layerable": 2, "Pinyin": "RKZDQ" }, { "_id": "54927306e84bdc1c8c545ae7", "Name": "那曲地区", "Code": "542400000000", "PName": "西藏自治区", "PCode": "540000000000", "Layerable": 2, "Pinyin": "NQDQ" }, { "_id": "54927306e84bdc1c8c54600b", "Name": "阿里地区", "Code": "542500000000", "PName": "西藏自治区", "PCode": "540000000000", "Layerable": 2, "Pinyin": "ALDQ" }, { "_id": "54927306e84bdc1c8c5460c7", "Name": "林芝地区", "Code": "542600000000", "PName": "西藏自治区", "PCode": "540000000000", "Layerable": 2, "Pinyin": "LZDQ" }], "610000000000": [{ "_id": "54927307e84bdc1c8c5462f6", "Name": "西安市", "Code": "610100000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "XAS" }, { "_id": "54927308e84bdc1c8c54726c", "Name": "铜川市", "Code": "610200000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "TCS" }, { "_id": "54927308e84bdc1c8c5474fc", "Name": "宝鸡市", "Code": "610300000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "BJS" }, { "_id": "54927308e84bdc1c8c547cef", "Name": "咸阳市", "Code": "610400000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "XYS" }, { "_id": "54927308e84bdc1c8c548995", "Name": "渭南市", "Code": "610500000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "WNS" }, { "_id": "54927308e84bdc1c8c549792", "Name": "延安市", "Code": "610600000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "YAS" }, { "_id": "54927309e84bdc1c8c54a5b7", "Name": "汉中市", "Code": "610700000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "HZS" }, { "_id": "54927309e84bdc1c8c54b18c", "Name": "榆林市", "Code": "610800000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "YLS" }, { "_id": "54927309e84bdc1c8c54c7da", "Name": "安康市", "Code": "610900000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "AKS" }, { "_id": "5492730ae84bdc1c8c54d21a", "Name": "商洛市", "Code": "611000000000", "PName": "陕西省", "PCode": "610000000000", "Layerable": 2, "Pinyin": "SLS" }], "620000000000": [{ "_id": "5492730be84bdc1c8c54d9db", "Name": "兰州市", "Code": "620100000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "LZS" }, { "_id": "5492730be84bdc1c8c54dec6", "Name": "嘉峪关市", "Code": "620200000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "JYGS" }, { "_id": "5492730be84bdc1c8c54defd", "Name": "金昌市", "Code": "620300000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "JCS" }, { "_id": "5492730be84bdc1c8c54dfb7", "Name": "白银市", "Code": "620400000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "BYS" }, { "_id": "5492730be84bdc1c8c54e327", "Name": "天水市", "Code": "620500000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "TSS" }, { "_id": "5492730be84bdc1c8c54edde", "Name": "武威市", "Code": "620600000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "WWS" }, { "_id": "5492730ce84bdc1c8c54f308", "Name": "张掖市", "Code": "620700000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "ZYS" }, { "_id": "5492730ce84bdc1c8c54f6e4", "Name": "平凉市", "Code": "620800000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "PLS" }, { "_id": "5492730ce84bdc1c8c54fd5c", "Name": "酒泉市", "Code": "620900000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "JQS" }, { "_id": "5492730ce84bdc1c8c54ffc8", "Name": "庆阳市", "Code": "621000000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "QYS" }, { "_id": "5492730ce84bdc1c8c5505aa", "Name": "定西市", "Code": "621100000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "DXS" }, { "_id": "5492730ce84bdc1c8c550d8b", "Name": "陇南市", "Code": "621200000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "LNS" }, { "_id": "5492730de84bdc1c8c551b32", "Name": "临夏回族自治州", "Code": "622900000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "LXHZZZZ" }, { "_id": "5492730de84bdc1c8c55206c", "Name": "甘南藏族自治州", "Code": "623000000000", "PName": "甘肃省", "PCode": "620000000000", "Layerable": 2, "Pinyin": "GNCZZZZ" }], "630000000000": [{ "_id": "5492730de84bdc1c8c552398", "Name": "西宁市", "Code": "630100000000", "PName": "青海省", "PCode": "630000000000", "Layerable": 2, "Pinyin": "XNS" }, { "_id": "5492730ee84bdc1c8c552821", "Name": "海东市", "Code": "630200000000", "PName": "青海省", "PCode": "630000000000", "Layerable": 2, "Pinyin": "HDS" }, { "_id": "5492730ee84bdc1c8c552efc", "Name": "海北藏族自治州", "Code": "632200000000", "PName": "青海省", "PCode": "630000000000", "Layerable": 2, "Pinyin": "HBCZZZZ" }, { "_id": "5492730ee84bdc1c8c553024", "Name": "黄南藏族自治州", "Code": "632300000000", "PName": "青海省", "PCode": "630000000000", "Layerable": 2, "Pinyin": "HNCZZZZ" }, { "_id": "5492730ee84bdc1c8c55315b", "Name": "海南藏族自治州", "Code": "632500000000", "PName": "青海省", "PCode": "630000000000", "Layerable": 2, "Pinyin": "HNCZZZZ" }, { "_id": "5492730ee84bdc1c8c55337d", "Name": "果洛藏族自治州", "Code": "632600000000", "PName": "青海省", "PCode": "630000000000", "Layerable": 2, "Pinyin": "GLCZZZZ" }, { "_id": "5492730ee84bdc1c8c553478", "Name": "玉树藏族自治州", "Code": "632700000000", "PName": "青海省", "PCode": "630000000000", "Layerable": 2, "Pinyin": "YSCZZZZ" }, { "_id": "5492730ee84bdc1c8c5535d7", "Name": "海西蒙古族藏族自治州", "Code": "632800000000", "PName": "青海省", "PCode": "630000000000", "Layerable": 2, "Pinyin": "HXMGZCZZZZ" }], "640000000000": [{ "_id": "5492730ee84bdc1c8c55378a", "Name": "银川市", "Code": "640100000000", "PName": "宁夏回族自治区", "PCode": "640000000000", "Layerable": 2, "Pinyin": "YCS" }, { "_id": "5492730fe84bdc1c8c5539c3", "Name": "石嘴山市", "Code": "640200000000", "PName": "宁夏回族自治区", "PCode": "640000000000", "Layerable": 2, "Pinyin": "SZSS" }, { "_id": "5492730fe84bdc1c8c553b37", "Name": "吴忠市", "Code": "640300000000", "PName": "宁夏回族自治区", "PCode": "640000000000", "Layerable": 2, "Pinyin": "WZS" }, { "_id": "5492730fe84bdc1c8c553dcc", "Name": "固原市", "Code": "640400000000", "PName": "宁夏回族自治区", "PCode": "640000000000", "Layerable": 2, "Pinyin": "GYS" }, { "_id": "5492730fe84bdc1c8c554190", "Name": "中卫市", "Code": "640500000000", "PName": "宁夏回族自治区", "PCode": "640000000000", "Layerable": 2, "Pinyin": "ZWS" }], "650000000000": [{ "_id": "54927310e84bdc1c8c5543bb", "Name": "乌鲁木齐市", "Code": "650100000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "WLMQS" }, { "_id": "54927310e84bdc1c8c5547ef", "Name": "克拉玛依市", "Code": "650200000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "KLMYS" }, { "_id": "54927310e84bdc1c8c5548a9", "Name": "吐鲁番地区", "Code": "652100000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "TLFDQ" }, { "_id": "54927310e84bdc1c8c5549c5", "Name": "哈密地区", "Code": "652200000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "HMDQ" }, { "_id": "54927310e84bdc1c8c554b53", "Name": "昌吉回族自治州", "Code": "652300000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "CJHZZZZ" }, { "_id": "54927310e84bdc1c8c554f48", "Name": "博尔塔拉蒙古自治州", "Code": "652700000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "BETLMGZZZ" }, { "_id": "54927310e84bdc1c8c55514f", "Name": "巴音郭楞蒙古自治州", "Code": "652800000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "BYGLMGZZZ" }, { "_id": "54927310e84bdc1c8c555505", "Name": "阿克苏地区", "Code": "652900000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "AKSDQ" }, { "_id": "54927311e84bdc1c8c555bc4", "Name": "克孜勒苏柯尔克孜自治州", "Code": "653000000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "KZLSKEKZZZZ" }, { "_id": "54927311e84bdc1c8c555d31", "Name": "喀什地区", "Code": "653100000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "KSDQ" }, { "_id": "54927311e84bdc1c8c556892", "Name": "和田地区", "Code": "653200000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "HTDQ" }, { "_id": "54927311e84bdc1c8c556eff", "Name": "伊犁哈萨克自治州", "Code": "654000000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "YLHSKZZZ" }, { "_id": "54927312e84bdc1c8c557471", "Name": "塔城地区", "Code": "654200000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "TCDQ" }, { "_id": "54927312e84bdc1c8c557af7", "Name": "阿勒泰地区", "Code": "654300000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "ALTDQ" }, { "_id": "54927312e84bdc1c8c557dfe", "Name": "自治区直辖县级行政区划", "Code": "659000000000", "PName": "新疆维吾尔自治区", "PCode": "650000000000", "Layerable": 2, "Pinyin": "ZZQZXXJXZQH" }], };
    //省地址
    var pJson = [{ "Code": "110000000000", "Name": "北京市", "Layerable": 1 }, { "Code": "120000000000", "Name": "天津市", "Layerable": 1 }, { "Code": "130000000000", "Name": "河北省", "Layerable": 1 }, { "Code": "140000000000", "Name": "山西省", "Layerable": 1 }, { "Code": "150000000000", "Name": "内蒙古", "Layerable": 1 }, { "Code": "210000000000", "Name": "辽宁省", "Layerable": 1 }, { "Code": "220000000000", "Name": "吉林省", "Layerable": 1 }, { "Code": "230000000000", "Name": "黑龙江省", "Layerable": 1 }, { "Code": "310000000000", "Name": "上海市", "Layerable": 1 }, { "Code": "320000000000", "Name": "江苏省", "Layerable": 1 }, { "Code": "330000000000", "Name": "浙江省", "Layerable": 1 }, { "Code": "340000000000", "Name": "安徽省", "Layerable": 1 }, { "Code": "350000000000", "Name": "福建省", "Layerable": 1 }, { "Code": "360000000000", "Name": "江西省", "Layerable": 1 }, { "Code": "370000000000", "Name": "山东省", "Layerable": 1 }, { "Code": "410000000000", "Name": "河南省", "Layerable": 1 }, { "Code": "420000000000", "Name": "湖北省", "Layerable": 1 }, { "Code": "430000000000", "Name": "湖南省", "Layerable": 1 }, { "Code": "440000000000", "Name": "广东省", "Layerable": 1 }, { "Code": "450000000000", "Name": "广西", "Layerable": 1 }, { "Code": "460000000000", "Name": "海南省", "Layerable": 1 }, { "Code": "500000000000", "Name": "重庆市", "Layerable": 1 }, { "Code": "510000000000", "Name": "四川省", "Layerable": 1 }, { "Code": "520000000000", "Name": "贵州省", "Layerable": 1 }, { "Code": "530000000000", "Name": "云南省", "Layerable": 1 }, { "Code": "540000000000", "Name": "西藏", "Layerable": 1 }, { "Code": "610000000000", "Name": "陕西省", "Layerable": 1 }, { "Code": "620000000000", "Name": "甘肃省", "Layerable": 1 }, { "Code": "630000000000", "Name": "青海省", "Layerable": 1 }, { "Code": "640000000000", "Name": "宁夏", "Layerable": 1 }, { "Code": "650000000000", "Name": "新疆", "Layerable": 1 }];

    var $first = $("#new_apply div.new_apply_first");
    $first.find(".province").show().html("全国");
    var provinceHtml = "<li code=\"{0}\">{1}</li>".format("top", "全国");
    var cityHtml = "";

    $.each(pJson, function (n, value) {
        provinceHtml += "<li code=\"{0}\">{1}</li>".format(value.Code, value.Name);
    });
    $first.find(".province_box").hide();
    $first.find(".city").hide().html("");
    $first.find(".city_box").hide();
    $first.find(".province_box ul").empty().append(provinceHtml).find("li").click(function () {

        $first.find(".city").show();
        $first.find(".city_box").hide();
        var code = $(this).attr("code");
        if (code == "top") {
            $first.find(".province").show().html("全国");
            $first.find(".province_box").hide();
            $first.find(".city").hide().html("");
            $first.find(".city_box").hide();
            return;
        }
        //点击某省后加载市区
        $.each(cityJson, function (j, cityvalue) {
            if (j == code) {
                for (var i = 0, l = cityvalue.length; i < l; i++) {
                    if (i == 0) {
                        if (typeof (cityvalue[0].Name) !== "undefined") {
                            $first.find(".city").html(cityvalue[0].Name.substring(0, 5));
                        }
                    }
                    cityHtml += "<li code=\"{0}\">{1}</li>".format(cityvalue[i].Code, cityvalue[i].Name.substring(0, 5));
                }
            }
        });
        $first.find(".city_box ul").empty().append(cityHtml);
        $first.find(".city_box ul li").click(function () {
            var cityname = $(this).html();
            $first.find(".city_box").hide();
            $first.find(".city").html(cityname);
        });
        cityHtml = "";
        $first.find(".city_box").show();
        $first.find(".province_box").hide();
        $first.find(".province").html($(this).html());
    });
}

//超过6个方案的提醒
function showInceptionBox() {
    var $inception = $("#inception");
    showApplication();
    $inception.show().siblings().hide();
}
//申请体验3D框
function showApplication() {
    hideUnity();
    $('#new_apply').show(80);
}
//右键冲突提醒框
function showConflictTips() {
    var operate = jQuery("#operateTips");
    operate.show();
}
//控制全屏F11
function requestFullScreen(element) {
    // Supports most browsers and their versions.
    var requestMethod = element.requestFullScreen || element.webkitRequestFullScreen || element.mozRequestFullScreen || element.msRequestFullscreen;

    if (requestMethod) { // Native full screen.
        requestMethod.call(element);
    } else if (typeof window.ActiveXObject !== "undefined") { // Older IE.
        try {
            var wscript = new ActiveXObject("WScript.Shell");
            if (wscript !== null) {
                wscript.SendKeys("{F11}");
            }
        } catch (err) {
            
        }
      
    }
}
//监控是否为全屏
//window.onresize = function () {
//    //console.log($("#unityPlayer")[0].offsetLeft);
//    //console.log($("#unityPlayer")[0].offsetHeight);
//    $("#unityPlayer")[0].offsetLeft = 342;
//    $("#unityPlayer")[0].offsetHeight =532;
//}

//默认户型框
function showApartmentBox() {debugger;
	alert();
    $("#new_apply div.new_apply_first_hasdefault").show().siblings().hide();
}
//隐藏登陆框
function HideLoginShowUnity(obj, data) {
    $(obj).hide();
    u.getUnity().SendMessage("Main Script", "SetUserToken", data);
    SendWebPlayerMessage(false, "");
    getProgressBar();
    showUnity();

}
//隐藏注册框
function HideRegisterShowUnity(obj, data) {
    $(obj).hide();
    u.getUnity().SendMessage("Main Script", "SetUserToken", data);
    showUnity();
}
//悬浮窗显示，4秒后自己关闭
function SuspendProcessBar() {
    $("div.loading-box").fadeIn();
    window.setTimeout(function() {
        $("div.loading-box").fadeOut();
    }, 4000);
}


//调用webPlayer一系列方法
function ComfirmAddress(data) {
    u.getUnity().SendMessage("Main Script", "ComfirmAddress",data);
    showUnity();
}
//发送消息给webplayer
function SendWebPlayerMessage(isError, message) {
    var enData = encodeURIComponent("{\"IsError\":" + isError + ",\"Message\":\"" + message + "\",\"Data\":\"\"}");
    u.getUnity().SendMessage("Main Script", "OnArrangeEvent", enData);
}
//切换空间
function UnityForceEnterRoom(roomName) {
    u.getUnity().SendMessage("Main Script", "ForceEnterRoom", roomName);
}
;
/*!
	Colorbox v1.4.33 - 2013-10-31
	jQuery lightbox and modal window plugin
	(c) 2013 Jack Moore - http://www.jacklmoore.com/colorbox
	license: http://www.opensource.org/licenses/mit-license.php
*/
(function ($, document, window) {
	var
	// Default settings object.
	// See http://jacklmoore.com/colorbox for details.
	defaults = {
		// data sources
		html: false,
		photo: false,
		iframe: false,
		inline: false,

		// behavior and appearance
		transition: "elastic",
		speed: 300,
		fadeOut: 300,
		width: false,
		initialWidth: "600",
		innerWidth: false,
		maxWidth: false,
		height: false,
		initialHeight: "450",
		innerHeight: false,
		maxHeight: false,
		scalePhotos: true,
		scrolling: true,
		href: false,
		title: false,
		rel: false,
		opacity: 0.9,
		preloading: true,
		className: false,
		overlayClose: true,
		escKey: true,
		arrowKey: true,
		top: false,
		bottom: false,
		left: false,
		right: false,
		fixed: false,
		data: undefined,
		closeButton: true,
		fastIframe: true,
		open: false,
		reposition: true,
		loop: true,
		slideshow: false,
		slideshowAuto: true,
		slideshowSpeed: 2500,
		slideshowStart: "start slideshow",
		slideshowStop: "stop slideshow",
		photoRegex: /\.(gif|png|jp(e|g|eg)|bmp|ico|webp)((#|\?).*)?$/i,

		// alternate image paths for high-res displays
		retinaImage: false,
		retinaUrl: false,
		retinaSuffix: '@2x.$1',

		// internationalization
		current: "image {current} of {total}",
		previous: "previous",
		next: "next",
		close: "close",
		xhrError: "This content failed to load.",
		imgError: "This image failed to load.",

		// accessbility
		returnFocus: true,
		trapFocus: true,

		// callbacks
		onOpen: false,
		onLoad: false,
		onComplete: false,
		onCleanup: false,
		onClosed: false
	},
	
	// Abstracting the HTML and event identifiers for easy rebranding
	colorbox = 'colorbox',
	prefix = 'cbox',
	boxElement = prefix + 'Element',
	
	// Events
	event_open = prefix + '_open',
	event_load = prefix + '_load',
	event_complete = prefix + '_complete',
	event_cleanup = prefix + '_cleanup',
	event_closed = prefix + '_closed',
	event_purge = prefix + '_purge',

	// Cached jQuery Object Variables
	$overlay,
	$box,
	$wrap,
	$content,
	$topBorder,
	$leftBorder,
	$rightBorder,
	$bottomBorder,
	$related,
	$window,
	$loaded,
	$loadingBay,
	$loadingOverlay,
	$title,
	$current,
	$slideshow,
	$next,
	$prev,
	$close,
	$groupControls,
	$events = $('<a/>'), // $([]) would be prefered, but there is an issue with jQuery 1.4.2
	
	// Variables for cached values or use across multiple functions
	settings,
	interfaceHeight,
	interfaceWidth,
	loadedHeight,
	loadedWidth,
	element,
	index,
	photo,
	open,
	active,
	closing,
	loadingTimer,
	publicMethod,
	div = "div",
	className,
	requests = 0,
	previousCSS = {},
	init;

	// ****************
	// HELPER FUNCTIONS
	// ****************
	
	// Convenience function for creating new jQuery objects
	function $tag(tag, id, css) {
		var element = document.createElement(tag);

		if (id) {
			element.id = prefix + id;
		}

		if (css) {
			element.style.cssText = css;
		}

		return $(element);
	}
	
	// Get the window height using innerHeight when available to avoid an issue with iOS
	// http://bugs.jquery.com/ticket/6724
	function winheight() {
		return window.innerHeight ? window.innerHeight : $(window).height();
	}

	// Determine the next and previous members in a group.
	function getIndex(increment) {
		var
		max = $related.length,
		newIndex = (index + increment) % max;
		
		return (newIndex < 0) ? max + newIndex : newIndex;
	}

	// Convert '%' and 'px' values to integers
	function setSize(size, dimension) {
		return Math.round((/%/.test(size) ? ((dimension === 'x' ? $window.width() : winheight()) / 100) : 1) * parseInt(size, 10));
	}
	
	// Checks an href to see if it is a photo.
	// There is a force photo option (photo: true) for hrefs that cannot be matched by the regex.
	function isImage(settings, url) {
		return settings.photo || settings.photoRegex.test(url);
	}

	function retinaUrl(settings, url) {
		return settings.retinaUrl && window.devicePixelRatio > 1 ? url.replace(settings.photoRegex, settings.retinaSuffix) : url;
	}

	function trapFocus(e) {
		if ('contains' in $box[0] && !$box[0].contains(e.target)) {
			e.stopPropagation();
			$box.focus();
		}
	}

	// Assigns function results to their respective properties
	function makeSettings() {
		var i,
			data = $.data(element, colorbox);
		
		if (data == null) {
			settings = $.extend({}, defaults);
			if (console && console.log) {
				console.log('Error: cboxElement missing settings object');
			}
		} else {
			settings = $.extend({}, data);
		}
		
		for (i in settings) {
			if ($.isFunction(settings[i]) && i.slice(0, 2) !== 'on') { // checks to make sure the function isn't one of the callbacks, they will be handled at the appropriate time.
				settings[i] = settings[i].call(element);
			}
		}
		
		settings.rel = settings.rel || element.rel || $(element).data('rel') || 'nofollow';
		settings.href = settings.href || $(element).attr('href');
		settings.title = settings.title || element.title;
		
		if (typeof settings.href === "string") {
			settings.href = $.trim(settings.href);
		}
	}

	function trigger(event, callback) {
		// for external use
		$(document).trigger(event);

		// for internal use
		$events.triggerHandler(event);

		if ($.isFunction(callback)) {
			callback.call(element);
		}
	}


	var slideshow = (function(){
		var active,
			className = prefix + "Slideshow_",
			click = "click." + prefix,
			timeOut;

		function clear () {
			clearTimeout(timeOut);
		}

		function set() {
			if (settings.loop || $related[index + 1]) {
				clear();
				timeOut = setTimeout(publicMethod.next, settings.slideshowSpeed);
			}
		}

		function start() {
			$slideshow
				.html(settings.slideshowStop)
				.unbind(click)
				.one(click, stop);

			$events
				.bind(event_complete, set)
				.bind(event_load, clear);

			$box.removeClass(className + "off").addClass(className + "on");
		}

		function stop() {
			clear();
			
			$events
				.unbind(event_complete, set)
				.unbind(event_load, clear);

			$slideshow
				.html(settings.slideshowStart)
				.unbind(click)
				.one(click, function () {
					publicMethod.next();
					start();
				});

			$box.removeClass(className + "on").addClass(className + "off");
		}

		function reset() {
			active = false;
			$slideshow.hide();
			clear();
			$events
				.unbind(event_complete, set)
				.unbind(event_load, clear);
			$box.removeClass(className + "off " + className + "on");
		}

		return function(){
			if (active) {
				if (!settings.slideshow) {
					$events.unbind(event_cleanup, reset);
					reset();
				}
			} else {
				if (settings.slideshow && $related[1]) {
					active = true;
					$events.one(event_cleanup, reset);
					if (settings.slideshowAuto) {
						start();
					} else {
						stop();
					}
					$slideshow.show();
				}
			}
		};

	}());


	function launch(target) {
		if (!closing) {
			
			element = target;
			
			makeSettings();
			
			$related = $(element);
			
			index = 0;
			
			if (settings.rel !== 'nofollow') {
				$related = $('.' + boxElement).filter(function () {
					var data = $.data(this, colorbox),
						relRelated;

					if (data) {
						relRelated =  $(this).data('rel') || data.rel || this.rel;
					}
					
					return (relRelated === settings.rel);
				});
				index = $related.index(element);
				
				// Check direct calls to Colorbox.
				if (index === -1) {
					$related = $related.add(element);
					index = $related.length - 1;
				}
			}
			
			$overlay.css({
				opacity: parseFloat(settings.opacity),
				cursor: settings.overlayClose ? "pointer" : "auto",
				visibility: 'visible'
			}).show();
			

			if (className) {
				$box.add($overlay).removeClass(className);
			}
			if (settings.className) {
				$box.add($overlay).addClass(settings.className);
			}
			className = settings.className;

			if (settings.closeButton) {
				$close.html(settings.close).appendTo($content);
			} else {
				$close.appendTo('<div/>');
			}

			if (!open) {
				open = active = true; // Prevents the page-change action from queuing up if the visitor holds down the left or right keys.
				
				// Show colorbox so the sizes can be calculated in older versions of jQuery
				$box.css({visibility:'hidden', display:'block'});
				
				$loaded = $tag(div, 'LoadedContent', 'width:0; height:0; overflow:hidden');
				$content.css({width:'', height:''}).append($loaded);

				// Cache values needed for size calculations
				interfaceHeight = $topBorder.height() + $bottomBorder.height() + $content.outerHeight(true) - $content.height();
				interfaceWidth = $leftBorder.width() + $rightBorder.width() + $content.outerWidth(true) - $content.width();
				loadedHeight = $loaded.outerHeight(true);
				loadedWidth = $loaded.outerWidth(true);

				// Opens inital empty Colorbox prior to content being loaded.
				settings.w = setSize(settings.initialWidth, 'x');
				settings.h = setSize(settings.initialHeight, 'y');
				$loaded.css({width:'', height:settings.h});
				publicMethod.position();

				trigger(event_open, settings.onOpen);
				
				$groupControls.add($title).hide();

				$box.focus();
				
				if (settings.trapFocus) {
					// Confine focus to the modal
					// Uses event capturing that is not supported in IE8-
					if (document.addEventListener) {

						document.addEventListener('focus', trapFocus, true);
						
						$events.one(event_closed, function () {
							document.removeEventListener('focus', trapFocus, true);
						});
					}
				}

				// Return focus on closing
				if (settings.returnFocus) {
					$events.one(event_closed, function () {
						$(element).focus();
					});
				}
			}
			load();
		}
	}

	// Colorbox's markup needs to be added to the DOM prior to being called
	// so that the browser will go ahead and load the CSS background images.
	function appendHTML() {
		if (!$box && document.body) {
			init = false;
			$window = $(window);
			$box = $tag(div).attr({
				id: colorbox,
				'class': $.support.opacity === false ? prefix + 'IE' : '', // class for optional IE8 & lower targeted CSS.
				role: 'dialog',
				tabindex: '-1'
			}).hide();
			$overlay = $tag(div, "Overlay").hide();
			$loadingOverlay = $([$tag(div, "LoadingOverlay")[0],$tag(div, "LoadingGraphic")[0]]);
			$wrap = $tag(div, "Wrapper");
			$content = $tag(div, "Content").append(
				$title = $tag(div, "Title"),
				$current = $tag(div, "Current"),
				$prev = $('<button type="button"/>').attr({id:prefix+'Previous'}),
				$next = $('<button type="button"/>').attr({id:prefix+'Next'}),
				$slideshow = $tag('button', "Slideshow"),
				$loadingOverlay
			);

			$close = $('<button type="button"/>').attr({id:prefix+'Close'});
			
			$wrap.append( // The 3x3 Grid that makes up Colorbox
				$tag(div).append(
					$tag(div, "TopLeft"),
					$topBorder = $tag(div, "TopCenter"),
					$tag(div, "TopRight")
				),
				$tag(div, false, 'clear:left').append(
					$leftBorder = $tag(div, "MiddleLeft"),
					$content,
					$rightBorder = $tag(div, "MiddleRight")
				),
				$tag(div, false, 'clear:left').append(
					$tag(div, "BottomLeft"),
					$bottomBorder = $tag(div, "BottomCenter"),
					$tag(div, "BottomRight")
				)
			).find('div div').css({'float': 'left'});
			
			$loadingBay = $tag(div, false, 'position:absolute; width:9999px; visibility:hidden; display:none; max-width:none;');
			
			$groupControls = $next.add($prev).add($current).add($slideshow);

			$(document.body).append($overlay, $box.append($wrap, $loadingBay));
		}
	}

	// Add Colorbox's event bindings
	function addBindings() {
		function clickHandler(e) {
			// ignore non-left-mouse-clicks and clicks modified with ctrl / command, shift, or alt.
			// See: http://jacklmoore.com/notes/click-events/
			if (!(e.which > 1 || e.shiftKey || e.altKey || e.metaKey || e.ctrlKey)) {
				e.preventDefault();
				launch(this);
			}
		}

		if ($box) {
			if (!init) {
				init = true;

				// Anonymous functions here keep the public method from being cached, thereby allowing them to be redefined on the fly.
				$next.click(function () {
					publicMethod.next();
				});
				$prev.click(function () {
					publicMethod.prev();
				});
				$close.click(function () {
					publicMethod.close();
				});
				$overlay.click(function () {
					if (settings.overlayClose) {
						publicMethod.close();
					}
				});
				
				// Key Bindings
				$(document).bind('keydown.' + prefix, function (e) {
					var key = e.keyCode;
					if (open && settings.escKey && key === 27) {
						e.preventDefault();
						publicMethod.close();
					}
					if (open && settings.arrowKey && $related[1] && !e.altKey) {
						if (key === 37) {
							e.preventDefault();
							$prev.click();
						} else if (key === 39) {
							e.preventDefault();
							$next.click();
						}
					}
				});

				if ($.isFunction($.fn.on)) {
					// For jQuery 1.7+
					$(document).on('click.'+prefix, '.'+boxElement, clickHandler);
				} else {
					// For jQuery 1.3.x -> 1.6.x
					// This code is never reached in jQuery 1.9, so do not contact me about 'live' being removed.
					// This is not here for jQuery 1.9, it's here for legacy users.
					$('.'+boxElement).live('click.'+prefix, clickHandler);
				}
			}
			return true;
		}
		return false;
	}

	// Don't do anything if Colorbox already exists.
	if ($.colorbox) {
		return;
	}

	// Append the HTML when the DOM loads
	$(appendHTML);


	// ****************
	// PUBLIC FUNCTIONS
	// Usage format: $.colorbox.close();
	// Usage from within an iframe: parent.jQuery.colorbox.close();
	// ****************
	
	publicMethod = $.fn[colorbox] = $[colorbox] = function (options, callback) {
		var $this = this;
		
		options = options || {};
		
		appendHTML();

		if (addBindings()) {
			if ($.isFunction($this)) { // assume a call to $.colorbox
				$this = $('<a/>');
				options.open = true;
			} else if (!$this[0]) { // colorbox being applied to empty collection
				return $this;
			}
			
			if (callback) {
				options.onComplete = callback;
			}
			
			$this.each(function () {
				$.data(this, colorbox, $.extend({}, $.data(this, colorbox) || defaults, options));
			}).addClass(boxElement);
			
			if (($.isFunction(options.open) && options.open.call($this)) || options.open) {
				launch($this[0]);
			}
		}
		
		return $this;
	};

	publicMethod.position = function (speed, loadedCallback) {
		var
		css,
		top = 0,
		left = 0,
		offset = $box.offset(),
		scrollTop,
		scrollLeft;
		
		$window.unbind('resize.' + prefix);

		// remove the modal so that it doesn't influence the document width/height
		$box.css({top: -9e4, left: -9e4});

		scrollTop = $window.scrollTop();
		scrollLeft = $window.scrollLeft();

		if (settings.fixed) {
			offset.top -= scrollTop;
			offset.left -= scrollLeft;
			$box.css({position: 'fixed'});
		} else {
			top = scrollTop;
			left = scrollLeft;
			$box.css({position: 'absolute'});
		}

		// keeps the top and left positions within the browser's viewport.
		if (settings.right !== false) {
			left += Math.max($window.width() - settings.w - loadedWidth - interfaceWidth - setSize(settings.right, 'x'), 0);
		} else if (settings.left !== false) {
			left += setSize(settings.left, 'x');
		} else {
			left += Math.round(Math.max($window.width() - settings.w - loadedWidth - interfaceWidth, 0) / 2);
		}
		
		if (settings.bottom !== false) {
			top += Math.max(winheight() - settings.h - loadedHeight - interfaceHeight - setSize(settings.bottom, 'y'), 0);
		} else if (settings.top !== false) {
			top += setSize(settings.top, 'y');
		} else {
			top += Math.round(Math.max(winheight() - settings.h - loadedHeight - interfaceHeight, 0) / 2);
		}

		$box.css({top: offset.top, left: offset.left, visibility:'visible'});
		
		// this gives the wrapper plenty of breathing room so it's floated contents can move around smoothly,
		// but it has to be shrank down around the size of div#colorbox when it's done.  If not,
		// it can invoke an obscure IE bug when using iframes.
		$wrap[0].style.width = $wrap[0].style.height = "9999px";
		
		function modalDimensions() {
			$topBorder[0].style.width = $bottomBorder[0].style.width = $content[0].style.width = (parseInt($box[0].style.width,10) - interfaceWidth)+'px';
			$content[0].style.height = $leftBorder[0].style.height = $rightBorder[0].style.height = (parseInt($box[0].style.height,10) - interfaceHeight)+'px';
		}

		css = {width: settings.w + loadedWidth + interfaceWidth, height: settings.h + loadedHeight + interfaceHeight, top: top, left: left};

		// setting the speed to 0 if the content hasn't changed size or position
		if (speed) {
			var tempSpeed = 0;
			$.each(css, function(i){
				if (css[i] !== previousCSS[i]) {
					tempSpeed = speed;
					return;
				}
			});
			speed = tempSpeed;
		}

		previousCSS = css;

		if (!speed) {
			$box.css(css);
		}

		$box.dequeue().animate(css, {
			duration: speed || 0,
			complete: function () {
				modalDimensions();
				
				active = false;
				
				// shrink the wrapper down to exactly the size of colorbox to avoid a bug in IE's iframe implementation.
				$wrap[0].style.width = (settings.w + loadedWidth + interfaceWidth) + "px";
				$wrap[0].style.height = (settings.h + loadedHeight + interfaceHeight) + "px";
				
				if (settings.reposition) {
					setTimeout(function () {  // small delay before binding onresize due to an IE8 bug.
						$window.bind('resize.' + prefix, publicMethod.position);
					}, 1);
				}

				if (loadedCallback) {
					loadedCallback();
				}
			},
			step: modalDimensions
		});
	};

	publicMethod.resize = function (options) {
		var scrolltop;
		
		if (open) {
			options = options || {};
			
			if (options.width) {
				settings.w = setSize(options.width, 'x') - loadedWidth - interfaceWidth;
			}

			if (options.innerWidth) {
				settings.w = setSize(options.innerWidth, 'x');
			}

			$loaded.css({width: settings.w});
			
			if (options.height) {
				settings.h = setSize(options.height, 'y') - loadedHeight - interfaceHeight;
			}

			if (options.innerHeight) {
				settings.h = setSize(options.innerHeight, 'y');
			}

			if (!options.innerHeight && !options.height) {
				scrolltop = $loaded.scrollTop();
				$loaded.css({height: "auto"});
				settings.h = $loaded.height();
			}

			$loaded.css({height: settings.h});

			if(scrolltop) {
				$loaded.scrollTop(scrolltop);
			}
			
			publicMethod.position(settings.transition === "none" ? 0 : settings.speed);
		}
	};

	publicMethod.prep = function (object) {
		if (!open) {
			return;
		}
		
		var callback, speed = settings.transition === "none" ? 0 : settings.speed;

		$loaded.empty().remove(); // Using empty first may prevent some IE7 issues.

		$loaded = $tag(div, 'LoadedContent').append(object);
		
		function getWidth() {
			settings.w = settings.w || $loaded.width();
			settings.w = settings.mw && settings.mw < settings.w ? settings.mw : settings.w;
			return settings.w;
		}
		function getHeight() {
			settings.h = settings.h || $loaded.height();
			settings.h = settings.mh && settings.mh < settings.h ? settings.mh : settings.h;
			return settings.h;
		}
		
		$loaded.hide()
		.appendTo($loadingBay.show())// content has to be appended to the DOM for accurate size calculations.
		.css({width: getWidth(), overflow: settings.scrolling ? 'auto' : 'hidden'})
		.css({height: getHeight()})// sets the height independently from the width in case the new width influences the value of height.
		.prependTo($content);
		
		$loadingBay.hide();
		
		// floating the IMG removes the bottom line-height and fixed a problem where IE miscalculates the width of the parent element as 100% of the document width.
		
		$(photo).css({'float': 'none'});

		callback = function () {
			var total = $related.length,
				iframe,
				frameBorder = 'frameBorder',
				allowTransparency = 'allowTransparency',
				complete;
			
			if (!open) {
				return;
			}
			
			function removeFilter() { // Needed for IE7 & IE8 in versions of jQuery prior to 1.7.2
				if ($.support.opacity === false) {
					$box[0].style.removeAttribute('filter');
				}
			}
			
			complete = function () {
				clearTimeout(loadingTimer);
				$loadingOverlay.hide();
				trigger(event_complete, settings.onComplete);
			};

			
			$title.html(settings.title).add($loaded).show();
			
			if (total > 1) { // handle grouping
				if (typeof settings.current === "string") {
					$current.html(settings.current.replace('{current}', index + 1).replace('{total}', total)).show();
				}
				
				$next[(settings.loop || index < total - 1) ? "show" : "hide"]().html(settings.next);
				$prev[(settings.loop || index) ? "show" : "hide"]().html(settings.previous);
				
				slideshow();
				
				// Preloads images within a rel group
				if (settings.preloading) {
					$.each([getIndex(-1), getIndex(1)], function(){
						var src,
							img,
							i = $related[this],
							data = $.data(i, colorbox);

						if (data && data.href) {
							src = data.href;
							if ($.isFunction(src)) {
								src = src.call(i);
							}
						} else {
							src = $(i).attr('href');
						}

						if (src && isImage(data, src)) {
							src = retinaUrl(data, src);
							img = document.createElement('img');
							img.src = src;
						}
					});
				}
			} else {
				$groupControls.hide();
			}
			
			if (settings.iframe) {
				iframe = $tag('iframe')[0];
				
				if (frameBorder in iframe) {
					iframe[frameBorder] = 0;
				}
				
				if (allowTransparency in iframe) {
					iframe[allowTransparency] = "true";
				}

				if (!settings.scrolling) {
					iframe.scrolling = "no";
				}
				
				$(iframe)
					.attr({
						src: settings.href,
						name: (new Date()).getTime(), // give the iframe a unique name to prevent caching
						'class': prefix + 'Iframe',
						allowFullScreen : true, // allow HTML5 video to go fullscreen
						webkitAllowFullScreen : true,
						mozallowfullscreen : true
					})
					.one('load', complete)
					.appendTo($loaded);
				
				$events.one(event_purge, function () {
					iframe.src = "//about:blank";
				});

				if (settings.fastIframe) {
					$(iframe).trigger('load');
				}
			} else {
				complete();
			}
			
			if (settings.transition === 'fade') {
				$box.fadeTo(speed, 1, removeFilter);
			} else {
				removeFilter();
			}
		};
		
		if (settings.transition === 'fade') {
			$box.fadeTo(speed, 0, function () {
				publicMethod.position(0, callback);
			});
		} else {
			publicMethod.position(speed, callback);
		}
	};

	function load () {
		var href, setResize, prep = publicMethod.prep, $inline, request = ++requests;
		
		active = true;
		
		photo = false;
		
		element = $related[index];
		
		makeSettings();
		
		trigger(event_purge);
		
		trigger(event_load, settings.onLoad);
		
		settings.h = settings.height ?
				setSize(settings.height, 'y') - loadedHeight - interfaceHeight :
				settings.innerHeight && setSize(settings.innerHeight, 'y');
		
		settings.w = settings.width ?
				setSize(settings.width, 'x') - loadedWidth - interfaceWidth :
				settings.innerWidth && setSize(settings.innerWidth, 'x');
		
		// Sets the minimum dimensions for use in image scaling
		settings.mw = settings.w;
		settings.mh = settings.h;
		
		// Re-evaluate the minimum width and height based on maxWidth and maxHeight values.
		// If the width or height exceed the maxWidth or maxHeight, use the maximum values instead.
		if (settings.maxWidth) {
			settings.mw = setSize(settings.maxWidth, 'x') - loadedWidth - interfaceWidth;
			settings.mw = settings.w && settings.w < settings.mw ? settings.w : settings.mw;
		}
		if (settings.maxHeight) {
			settings.mh = setSize(settings.maxHeight, 'y') - loadedHeight - interfaceHeight;
			settings.mh = settings.h && settings.h < settings.mh ? settings.h : settings.mh;
		}
		
		href = settings.href;
		
		loadingTimer = setTimeout(function () {
			$loadingOverlay.show();
		}, 100);
		
		if (settings.inline) {
			// Inserts an empty placeholder where inline content is being pulled from.
			// An event is bound to put inline content back when Colorbox closes or loads new content.
			$inline = $tag(div).hide().insertBefore($(href)[0]);

			$events.one(event_purge, function () {
				$inline.replaceWith($loaded.children());
			});

			prep($(href));
		} else if (settings.iframe) {
			// IFrame element won't be added to the DOM until it is ready to be displayed,
			// to avoid problems with DOM-ready JS that might be trying to run in that iframe.
			prep(" ");
		} else if (settings.html) {
			prep(settings.html);
		} else if (isImage(settings, href)) {

			href = retinaUrl(settings, href);

			photo = document.createElement('img');

			$(photo)
			.addClass(prefix + 'Photo')
			.bind('error',function () {
				settings.title = false;
				prep($tag(div, 'Error').html(settings.imgError));
			})
			.one('load', function () {
				var percent;

				if (request !== requests) {
					return;
				}

				$.each(['alt', 'longdesc', 'aria-describedby'], function(i,val){
					var attr = $(element).attr(val) || $(element).attr('data-'+val);
					if (attr) {
						photo.setAttribute(val, attr);
					}
				});

				if (settings.retinaImage && window.devicePixelRatio > 1) {
					photo.height = photo.height / window.devicePixelRatio;
					photo.width = photo.width / window.devicePixelRatio;
				}

				if (settings.scalePhotos) {
					setResize = function () {
						photo.height -= photo.height * percent;
						photo.width -= photo.width * percent;
					};
					if (settings.mw && photo.width > settings.mw) {
						percent = (photo.width - settings.mw) / photo.width;
						setResize();
					}
					if (settings.mh && photo.height > settings.mh) {
						percent = (photo.height - settings.mh) / photo.height;
						setResize();
					}
				}
				
				if (settings.h) {
					photo.style.marginTop = Math.max(settings.mh - photo.height, 0) / 2 + 'px';
				}
				
				if ($related[1] && (settings.loop || $related[index + 1])) {
					photo.style.cursor = 'pointer';
					photo.onclick = function () {
						publicMethod.next();
					};
				}

				photo.style.width = photo.width + 'px';
				photo.style.height = photo.height + 'px';

				setTimeout(function () { // A pause because Chrome will sometimes report a 0 by 0 size otherwise.
					prep(photo);
				}, 1);
			});
			
			setTimeout(function () { // A pause because Opera 10.6+ will sometimes not run the onload function otherwise.
				photo.src = href;
			}, 1);
		} else if (href) {
			$loadingBay.load(href, settings.data, function (data, status) {
				if (request === requests) {
					prep(status === 'error' ? $tag(div, 'Error').html(settings.xhrError) : $(this).contents());
				}
			});
		}
	}
		
	// Navigates to the next page/image in a set.
	publicMethod.next = function () {
		if (!active && $related[1] && (settings.loop || $related[index + 1])) {
			index = getIndex(1);
			launch($related[index]);
		}
	};
	
	publicMethod.prev = function () {
		if (!active && $related[1] && (settings.loop || index)) {
			index = getIndex(-1);
			launch($related[index]);
		}
	};

	// Note: to use this within an iframe use the following format: parent.jQuery.colorbox.close();
	publicMethod.close = function () {
		if (open && !closing) {
			
			closing = true;
			
			open = false;
			
			trigger(event_cleanup, settings.onCleanup);
			
			$window.unbind('.' + prefix);
			
			$overlay.fadeTo(settings.fadeOut || 0, 0);
			
			$box.stop().fadeTo(settings.fadeOut || 0, 0, function () {
			
				$box.add($overlay).css({'opacity': 1, cursor: 'auto'}).hide();
				
				trigger(event_purge);
				
				$loaded.empty().remove(); // Using empty first may prevent some IE7 issues.
				
				setTimeout(function () {
					closing = false;
					trigger(event_closed, settings.onClosed);
				}, 1);
			});
		}
	};

	// Removes changes Colorbox made to the document, but does not remove the plugin.
	publicMethod.remove = function () {
		if (!$box) { return; }

		$box.stop();
		$.colorbox.close();
		$box.stop().remove();
		$overlay.remove();
		closing = false;
		$box = null;
		$('.' + boxElement)
			.removeData(colorbox)
			.removeClass(boxElement);

		$(document).unbind('click.'+prefix);
	};

	// A method for fetching the current element Colorbox is referencing.
	// returns a jQuery object.
	publicMethod.element = function () {
		return $(element);
	};

	publicMethod.settings = defaults;

}(jQuery, document, window));
;
/*
* jQuery jBox 2.3
* http://www.kudystudio.com
* Author: kudy chen (kudychen@gmail.com)
* 
* Copyright 2011, kudy studio
* Dual licensed under the MIT or GPL Version 3 licenses.
* 
* Last Modified: 2011-11-08
*/
/*
* jQuery jBox 2.3
* Author: kudy chen (kudychen@gmail.com)
* 
* Copyright 2011, kudy studio
* Dual licensed under the MIT or GPL Version 3 licenses.
* 
* Last Modified: 2011-11-11
*/
(function ($) {
    $.jBox = function (content, options) {
        options = $.extend({}, $.jBox.defaults, options);
        options.showFade = options.opacity > 0;
        options.isTip = options.isTip || false;
        options.isMessager = options.isMessager || false;

        if (content == undefined) { content = ''; }
        if (options.border < 0) { options.border = 0; }
        if (options.id == undefined) { options.id = 'jBox_' + Math.floor(Math.random() * 1000000); }
        var isIE6 = ($.browser.msie && parseInt($.browser.version) < 7);

        var prevBox = $('#' + options.id);
        if (prevBox.length > 0) {
            options.zIndex = $.jBox.defaults.zIndex++;
            prevBox.css({ zIndex: options.zIndex });
            prevBox.find('#jbox').css({ zIndex: options.zIndex + 1 });
            return prevBox;
        }

        var data = { url: '', type: '', html: '', isObject: content.constructor == Object };
        if (!data.isObject) {
            content = content + '';
            var tempContent = content.toLowerCase();
            if (tempContent.indexOf('id:') == 0) data.type = 'ID';
            else if (tempContent.indexOf('get:') == 0) data.type = 'GET';
            else if (tempContent.indexOf('post:') == 0) data.type = 'POST';
            else if (tempContent.indexOf('iframe:') == 0) data.type = 'IFRAME';
            else if (tempContent.indexOf('html:') == 0) data.type = 'HTML';
            else { content = 'html:' + content; data.type = 'HTML'; }
            content = content.substring(content.indexOf(":") + 1, content.length);
        }

        var withTitle = !options.isTip && !(options.title == undefined);
        var isRequest = data.type == 'GET' || data.type == 'POST' || data.type == 'IFRAME';
        var titleWidth = typeof options.width == 'number' ? (options.width - 50) + 'px' : "90%";

        var boxHtml = [];
        boxHtml.push('<div id="' + options.id + '" class="jbox-' + (options.isTip ? 'tip' : (options.isMessager ? 'messager' : 'body')) + '">');
        if (options.showFade) {
            if ((isIE6 && $('iframe').length > 0) || $('object, applet').length > 0) {
                boxHtml.push('<iframe id="jbox-fade" class="jbox-fade" src="about:blank" style="display:block;position:absolute;z-index:-1;"></iframe>');
            }
            else {
                if (isIE6) {
                    $('select').css('visibility', 'hidden');
                }
                boxHtml.push('<div id="jbox-fade" class="jbox-fade" style="position:absolute;"></div>');
            }
        }
        boxHtml.push('<div id="jbox-temp" class="jbox-temp" style="width:0px;height:0px;background-color:#ff3300;position:absolute;z-index:1984;fdisplay:none;"></div>');
        if (options.draggable) {
            boxHtml.push('<div id="jbox-drag" class="jbox-drag" style="position:absolute;z-index:1984;display:none;"></div>');
        }
        boxHtml.push('<div id="jbox" class="jbox" style="position:absolute;width:auto;height:auto;">');

        boxHtml.push('<div class="jbox-help-title jbox-title-panel" style="height:25px;display:none;"></div>');
        boxHtml.push('<div class="jbox-help-button jbox-button-panel" style="height:25px;padding:5px 0 5px 0;display:none;"></div>');

        boxHtml.push('<table border="0" cellpadding="0" cellspacing="0" style="margin:0px;padding:0px;border:none;">');
        if (options.border > 0) {
            boxHtml.push('<tr>');
            boxHtml.push('<td class="jbox-border" style="margin:0px;padding:0px;border:none;border-radius:' + options.border + 'px 0 0 0;width:' + options.border + 'px;height:' + options.border + 'px;"></td>');
            boxHtml.push('<td class="jbox-border" style="margin:0px;padding:0px;border:none;height:' + options.border + 'px;overflow: hidden;"></td>');
            boxHtml.push('<td class="jbox-border" style="margin:0px;padding:0px;border:none;border-radius:0 ' + options.border + 'px 0 0;width:' + options.border + 'px;height:' + options.border + 'px;"></td>');
            boxHtml.push('</tr>');
        }
        boxHtml.push('<tr>');
        boxHtml.push('<td class="jbox-border" style="margin:0px;padding:0px;border:none;"></td>');
        boxHtml.push('<td valign="top" style="margin:0px;padding:0px;border:none;">');
        boxHtml.push('<div class="jbox-container" style="width:auto; height:auto;">');

        boxHtml.push('<a class="jbox-close" title="' + $.jBox.languageDefaults.close + '" onmouseover="$(this).addClass(\'jbox-close-hover\');" onmouseout="$(this).removeClass(\'jbox-close-hover\');" style="position:absolute; display:block; cursor:pointer; top:' + (6 + options.border) + 'px; right:' + (6 + options.border) + 'px; width:15px; height:15px;' + (options.showClose ? '' : 'display:none;') + '"></a>');

        if (withTitle) {
            boxHtml.push('<div class="jbox-title-panel" style="height:25px;">');
            boxHtml.push('<div class="jbox-title' + (options.showIcon == true ? ' jbox-title-icon' : (options.showIcon == false ? '' : ' ' + options.showIcon)) + '" style="float:left; width:' + titleWidth + '; line-height:' + ($.browser.msie ? 25 : 24) + 'px; padding-left:' + (options.showIcon ? 18 : 5) + 'px;overflow:hidden;text-overflow:ellipsis;word-break:break-all;">' + (options.title == '' ? '&nbsp;' : options.title) + '</div>');
            boxHtml.push('</div>');
        }
        boxHtml.push('<div id="jbox-states"></div></div>');

        boxHtml.push('</div>');
        boxHtml.push('</td>');
        boxHtml.push('<td class="jbox-border" style="margin:0px;padding:0px;border:none;"></td>');
        boxHtml.push('</tr>');
        if (options.border > 0) {
            boxHtml.push('<tr>');
            boxHtml.push('<td class="jbox-border" style="margin:0px;padding:0px;border:none;border-radius:0 0 0 ' + options.border + 'px; width:' + options.border + 'px; height:' + options.border + 'px;"></td>');
            boxHtml.push('<td class="jbox-border" style="margin:0px;padding:0px;border:none;height:' + options.border + 'px;overflow: hidden;"></td>');
            boxHtml.push('<td class="jbox-border" style="margin:0px;padding:0px;border:none;border-radius:0 0 ' + options.border + 'px 0; width:' + options.border + 'px; height:' + options.border + 'px;"></td>');
            boxHtml.push('</tr>');
        }
        boxHtml.push('</table>');
        boxHtml.push('</div>');
        boxHtml.push('</div>');

        var iframeHtml = '<iframe name="jbox-iframe" id="jbox-iframe" width="100%" height="100%" marginheight="0" marginwidth="0" frameborder="0" scrolling="' + options.iframeScrolling + '"></iframe>';

        var $window = $(window);
        var $body = $(document.body);
        var $boxBody = $(boxHtml.join('')).appendTo($body);
        var $box = $boxBody.children('#jbox');
        var $boxFade = $boxBody.children('#jbox-fade');
        var $boxTemp = $boxBody.children('#jbox-temp');

        if (!data.isObject) {
            switch (data.type) {
                case "ID":
                    data.html = $('#' + content).html();
                    break;
                case "GET":
                case "POST":
                    data.html = '';
                    data.url = content;
                    break;
                case "HTML":
                    data.html = content;
                    break;
                case "IFRAME":
                    data.html = iframeHtml;
                    if (content.indexOf('#') == -1) {
                        data.url = content + (content.indexOf('?') == -1 ? '?___t=' : '&___t=') + Math.random();
                    } else {
                        var arr = content.split('#');
                        data.url = arr[0] + (arr[0].indexOf('?') == -1 ? '?___t=' : '&___t=') + Math.random() + '#' + arr[1];
                    }
                    break;
            }
            content = {
                state0: {
                    content: data.html, buttons: options.buttons, buttonsFocus: options.buttonsFocus, submit: options.submit
                }
            };
        }

        var states = [];
        var helpTitleHeight = $box.find('.jbox-help-title').outerHeight(true);
        var helpButtonHeight = $box.find('.jbox-help-button').outerHeight(true);
        var ieButtonFix = $.browser.msie ? 'line-height:19px;padding:0px 6px 0px 6px;' : 'padding:0px 10px 0px 10px;';
        $.each(content, function (stateName, stateOptions) {
            if (data.isObject) {
                stateOptions = $.extend({}, $.jBox.stateDefaults, stateOptions);
            }
            content[stateName] = stateOptions;
            if (stateOptions.buttons == undefined) { stateOptions.buttons = {}; }
            var withButtons = false;
            $.each(stateOptions.buttons, function (k, v) { withButtons = true; });
            var contentHeight = 'auto';
            if (typeof options.height == 'number') {
                contentHeight = options.height;
                if (withTitle) { contentHeight = contentHeight - helpTitleHeight; }
                if (withButtons) { contentHeight = contentHeight - helpButtonHeight; }
                contentHeight = (contentHeight - 1) + 'px';
            }

            var loadingHtml = '';
            var loadingImageTop = '25px';
            if (!data.isObject && isRequest) {
                var loadingHeight = options.height;
                if (typeof options.height == 'number') {
                    if (withTitle) { loadingHeight = loadingHeight - helpTitleHeight; }
                    if (withButtons) { loadingHeight = loadingHeight - helpButtonHeight; }
                    loadingImageTop = ((loadingHeight / 5) * 2) + 'px';
                    loadingHeight = (loadingHeight - 1) + 'px';
                }
                loadingHtml = ['<div id="jbox-content-loading" class="jbox-content-loading" style="min-height:70px;height:' + loadingHeight + '; text-align:center;">',
                '<div class="jbox-content-loading-image" style="display:block; margin:auto; width:220px; height:19px; padding-top: ' + loadingImageTop + ';"></div>',
                '</div>'].join('');
            }

            states.push('<div id="jbox-state-' + stateName + '" class="jbox-state" style="display:none;">');
            states.push('<div style="min-width:50px;width:' + (typeof options.width == 'number' ? options.width + 'px' : 'auto') + '; height:' + contentHeight + ';">' + loadingHtml + '<div id="jbox-content" class="jbox-content" style="height:' + contentHeight + ';overflow:hidden;overflow-y:auto;">' + stateOptions.content + '</div></div>');
            states.push('<div class="jbox-button-panel" style="height:25px;padding:5px 0 5px 0;text-align: right;' + (withButtons ? '' : 'display:none;') + '">');
            if (!options.isTip) {
                states.push('<span class="jbox-bottom-text" style="float:left;display:block;line-height:25px;"></span>');
            }
            $.each(stateOptions.buttons, function (k, v) {
                states.push('<button class="jbox-button" value="' + v + '" style="' + ieButtonFix + '">' + k + '</button>');
            });
            states.push('</div></div>');
        });

        $box.find('#jbox-states').html(states.join('')).children('.jbox-state:first').css('display', 'block');
        /*$box.find('.jbox-button-panel:empty').css('display', 'none');*/
        if (isRequest) {
            var $iframe = $box.find('#jbox-content').css({ position: (isIE6) ? "absolute" : "fixed", left: -10000 });
        }

        $.each(content, function (stateName, stateOptions) {
            var $state = $box.find('#jbox-state-' + stateName);
            $state.children('.jbox-button-panel').children('button').click(function () {
                var object = $state.find('#jbox-content');
                var clicked = stateOptions.buttons[$(this).text()];
                var formInputs = {};

                $.each($box.find('#jbox-states :input').serializeArray(), function (i, obj) {
                    if (formInputs[obj.name] === undefined) {
                        formInputs[obj.name] = obj.value;
                    } else if (typeof formInputs[obj.name] == Array) {
                        formInputs[obj.name].push(obj.value);
                    } else {
                        formInputs[obj.name] = [formInputs[obj.name], obj.value];
                    }
                });
                var closed = stateOptions.submit(clicked, object, formInputs);
                if (closed === undefined || closed) {
                    removeBox();
                }
            })
            /*.bind('focus', function () { $(this).blur(); })*/
            .bind('mousedown', function () { $(this).addClass('jbox-button-active'); })
            .bind('mouseup', function () { $(this).removeClass('jbox-button-active'); })
            .bind('mouseover', function () { $(this).addClass('jbox-button-hover'); })
            .bind('mouseout', function () { $(this).removeClass('jbox-button-active').removeClass('jbox-button-hover'); });

            $state.find('.jbox-button-panel button:eq(' + stateOptions.buttonsFocus + ')').addClass('jbox-button-focus');
        });

        var ie6scroll = function () {
            $boxBody.css({ top: $window.scrollTop() });
            if (options.isMessager) {
                $box.css({
                    position: (isIE6) ? "absolute" : "fixed",
                    right: 1,
                    bottom: 1
                });
            }
        };
        var fadeClicked = function () {
            if (!options.showFade) {
                return;
            }
            if (options.persistent) {
                var i = 0;
                $boxBody.addClass('jbox-warning');
                var intervalid = setInterval(function () {
                    $boxBody.toggleClass('jbox-warning');
                    if (i++ > 1) {
                        clearInterval(intervalid);
                        $boxBody.removeClass('jbox-warning');
                    }
                }, 100);
            } else {
                removeBox();
            }
        };
        var keyPressEventHandler = function (e) {
            if (options.isTip || options.isMessager) {
                return false;
            }
            var key = (window.event) ? event.keyCode : e.keyCode;
            if (key == 27) {
                removeBox();
            }
            if (key == 9) {
                var $inputels = $(':input:enabled:visible', $boxBody);
                var fwd = !e.shiftKey && e.target == $inputels[$inputels.length - 1];
                var back = e.shiftKey && e.target == $inputels[0];
                if (fwd || back) {
                    setTimeout(function () {
                        if (!$inputels) return;
                        var el = $inputels[back === true ? $inputels.length - 1 : 0];
                        if (el) el.focus();
                    }, 10);
                    return false;
                }
            }
        };
        var setFade = function () {
            if (options.showFade) {
                $.jBox.FadeBoxCount++;
                $($.browser.msie ? 'html' : 'body').attr('style', 'overflow:hidden;padding-right:17px;');

                $boxFade.css({
                    position: "absolute",
                    height: options.isTip ? 5000 : $window.height(),
                    width: isIE6 ? $window.width() : "100%",
                    top: 0,
                    left: 0,
                    right: 0,
                    bottom: 0
                });
            }
        };
        var positionBox = function () {
            if (options.isMessager) {
                $box.css({
                    position: (isIE6) ? "absolute" : "fixed",
                    right: 1,
                    bottom: 1
                });
            } else {
                $boxTemp.css({ top: options.top });
                $box.css({
                    position: "absolute",
                    top: $boxTemp.offset().top + (options.isTip ? $window.scrollTop() : 0),
                    left: (($window.width() - $box.outerWidth()) / 2)
                });
            }
            if ((options.showFade && !options.isTip) || (!options.showFade && !options.isTip && !options.isMessager)) {
                $boxBody.css({
                    position: (isIE6) ? "absolute" : "fixed",
                    height: options.showFade ? $window.height() : 0,
                    width: "100%",
                    top: (isIE6) ? $window.scrollTop() : 0,
                    left: 0,
                    right: 0,
                    bottom: 0
                });
            }
            setFade();
        };
        var setTop = function () {
            options.zIndex = $.jBox.defaults.zIndex++;
            $boxBody.css({ zIndex: options.zIndex });
            $box.css({ zIndex: options.zIndex + 1 });
        };
        var styleBox = function () {
            options.zIndex = $.jBox.defaults.zIndex++;
            $boxBody.css({ zIndex: options.zIndex });
            $box.css({
                display: "none",
                zIndex: options.zIndex + 1
            });
            if (options.showFade) {
                $boxFade.css({
                    display: "none",
                    zIndex: options.zIndex,
                    opacity: options.opacity
                });
            }
        };
        var doDown = function (e) {
            var dragData = e.data;
            dragData.target.find('iframe').hide();
            if (options.dragClone) {
                dragData.target.prev().css({
                    left: dragData.target.css('left'),
                    top: dragData.target.css('top'),
                    marginLeft: -2,
                    marginTop: -2,
                    width: dragData.target.width() + 2,
                    height: dragData.target.height() + 2
                }).show();
            }
            return false;
        }
        var doMove = function (e) {
            var dragData = e.data;
            var left = dragData.startLeft + e.pageX - dragData.startX;
            var top = dragData.startTop + e.pageY - dragData.startY;
            if (options.dragLimit) {
                var minTop = 1;
                var maxTop = document.documentElement.clientHeight - e.data.target.height() - 1;
                var minLeft = 1;
                var maxLeft = document.documentElement.clientWidth - e.data.target.width() - 1;
                if (top < minTop) top = minTop + (options.dragClone ? 2 : 0);
                if (top > maxTop) top = maxTop - (options.dragClone ? 2 : 0);
                if (left < minLeft) left = minLeft + (options.dragClone ? 2 : 0);
                if (left > maxLeft) left = maxLeft - (options.dragClone ? 2 : 0);
            }
            if (options.dragClone) {
                dragData.target.prev().css({ left: left, top: top });
            } else {
                dragData.target.css({ left: left, top: top });
            }
            return false;
        }
        var doUp = function (e) {
            $(document).unbind('.draggable');
            if (options.dragClone) {
                var drag = e.data.target.prev().hide();
                e.data.target.css({ left: drag.css('left'), top: drag.css('top') }).find('iframe').show();
            } else {
                e.data.target.find('iframe').show();
            }
            return false;
        }
        var onMouseDown = function (e) {
            var position = e.data.target.position();
            var data = {
                target: e.data.target,
                startX: e.pageX,
                startY: e.pageY,
                startLeft: position.left,
                startTop: position.top
            };
            $(document).bind('mousedown.draggable', data, doDown).bind('mousemove.draggable', data, doMove).bind('mouseup.draggable', data, doUp);
        }
        var removeBox = function () {
            if (options.showFade) {
                if ($.jBox.FadeBoxCount == 1) {
                    $($.browser.msie ? 'html' : 'body').removeAttr('style');
                }
                $.jBox.FadeBoxCount--;
            }

            if (options.isTip) {
                var tip = $(document.body).data('tip');
                if (tip && tip.next == true) {
                    $boxTemp.css('top', tip.options.top);
                    var top = $boxTemp.offset().top + $window.scrollTop();
                    if (top == $box.offset().top) {
                        removeBoxImpl();
                    }
                    else {
                        $box.find('#jbox-content').html(tip.options.content.substr(5)).end().css({ left: (($window.width() - $box.outerWidth()) / 2) }).animate({ top: top, opacity: 0.1 }, 500, removeBoxImpl);
                    }
                }
                else {
                    $box.animate({ top: '-=200', opacity: 0 }, 500, removeBoxImpl);
                }
            }
            else {
                switch (options.showType) {
                    case 'slide':
                        $box.slideUp(options.showSpeed, removeBoxImpl);
                        break;
                    case 'fade':
                        $box.fadeOut(options.showSpeed, removeBoxImpl);
                        break;
                    case 'show':
                    default:
                        $box.hide(options.showSpeed, removeBoxImpl);
                        break;
                }
            }
        };
        var removeBoxImpl = function () {
            $window.unbind('resize', setFade);
            if (options.draggable && !options.isTip && !options.isMessager) {
                $box.find('.jbox-title-panel').unbind('mousedown', onMouseDown);
            }
            if (data.type != 'IFRAME') {
                $box.find('#jbox-iframe').attr({ 'src': 'about:blank' });
            }
            $box.html('').remove();
            if (isIE6 && !options.isTip) {
                $body.unbind('scroll', ie6scroll);
            }
            if (options.showFade) {
                $boxFade.fadeOut('fast', function () {
                    $boxFade.unbind('click', fadeClicked).unbind('mousedown', setTop).html('').remove();
                });
            }
            $boxBody.unbind('keydown keypress', keyPressEventHandler).html('').remove();
            if (isIE6 && options.showFade) {
                $('select').css('visibility', 'visible');
                //$(data.selects).attr('disabled', false);
            }
            if (typeof options.closed == 'function') { options.closed(); }
        };
        var autoClosing = function () {
            if (options.timeout > 0) {
                $box.data('autoClosing', window.setTimeout(removeBox, options.timeout));
                if (options.isMessager) {
                    $box.hover(function () {
                        window.clearTimeout($box.data('autoClosing'));
                    }, function () {
                        $box.data('autoClosing', window.setTimeout(removeBox, options.timeout));
                    });
                }
            }
        };
        var loaded = function () {
            if (typeof options.loaded == 'function') {
                options.loaded($box.find('.jbox-state:visible').find('.jbox-content'));
            }
        };

        if (!data.isObject) {
            switch (data.type) {
                case "GET":
                case "POST":
                    $.ajax({
                        type: data.type,
                        url: data.url,
                        data: options.ajaxData == undefined ? {} : options.ajaxData,
                        dataType: 'html',
                        cache: false,
                        success: function (data, textStatus) {
                            $box.find('#jbox-content').css({ position: "static" }).html(data).show().prev().hide();
                            loaded();
                        },
                        error: function () {
                            $box.find('#jbox-content-loading').html('<div style="padding-top:50px;padding-bottom:50px;text-align:center;">Loading Error.</div>');
                        }
                    });
                    break;
                case "IFRAME":
                    $box.find('#jbox-iframe').attr({ 'src': data.url }).bind("load", function (event) {
                        $(this).parent().css({ position: "static" }).show().prev().hide();
                        $box.find('#jbox-states .jbox-state:first .jbox-button-focus').focus();
                        loaded();
                    });
                    break;
                default:
                    $box.find('#jbox-content').show();
                    break;
            }
        }
        positionBox();
        styleBox();

        if (isIE6 && !options.isTip) {
            $window.scroll(ie6scroll);
        }
        if (options.showFade) {
            $boxFade.click(fadeClicked);
        }
        $window.resize(setFade);
        $boxBody.bind('keydown keypress', keyPressEventHandler);
        $box.find('.jbox-close').click(removeBox);

        if (options.showFade) {
            $boxFade.fadeIn('fast');
        }
        var showFunc = 'show';
        if (options.showType == 'slide') {
            showFunc = 'slideDown';
        } else if (options.showType == 'fade') {
            showFunc = 'fadeIn';
        }
        if (options.isMessager) {
            $box[showFunc](options.showSpeed, autoClosing);
        } else {
            var tip = $(document.body).data('tip');
            if (tip && tip.next == true) {
                $(document.body).data('tip', { next: false, options: {} });
                $box.css('display', '');
            } else {
                if (!data.isObject && isRequest) {
                    $box[showFunc](options.showSpeed);
                } else {
                    $box[showFunc](options.showSpeed, loaded); /* loaded */
                }
            }
        }
        if (!options.isTip) {
            $box.find('.jbox-bottom-text').html(options.bottomText);
        } else {
            $box.find('.jbox-container,.jbox-content').addClass('jbox-tip-color');
        }
        if (data.type != 'IFRAME') {
            $box.find('#jbox-states .jbox-state:first .jbox-button-focus').focus();
        }
        else {
            $box.focus();
        }
        if (!options.isMessager) {
            autoClosing();
        }
        $boxBody.bind('mousedown', setTop);
        if (options.draggable && !options.isTip && !options.isMessager) {
            $box.find('.jbox-title-panel').bind('mousedown', { target: $box }, onMouseDown).css('cursor', 'move');
        }
        return $boxBody;
    };

    $.jBox.version = 2.3;
    $.jBox.defaults = {
        id: null,
        top: "15%",
        zIndex: 1984,
        border: 5,
        opacity: 0.1,
        timeout: 0,
        showType: 'fade',
        showSpeed: 'fast',
        showIcon: true,
        showClose: true,
        draggable: true,
        dragLimit: true,
        dragClone: false,
        persistent: true,
        showScrolling: true,
        ajaxData: {},
        iframeScrolling: 'auto',

        title: 'jBox',
        width: 350,
        height: 'auto',
        bottomText: '',
        buttons: { '确定': 'ok' },
        buttonsFocus: 0,
        loaded: function (h) { },
        submit: function (v, h, f) { return true; },
        closed: function () { }
    };
    $.jBox.stateDefaults = { content: '', buttons: { '确定': 'ok' }, buttonsFocus: 0, submit: function (v, h, f) { return true; } };
    $.jBox.tipDefaults = { content: '', icon: 'info', top: '40%', width: 'auto', height: 'auto', opacity: 0, timeout: 3000, closed: function () { } };
    $.jBox.messagerDefaults = { content: '', title: 'jBox', icon: 'none', width: 350, height: 'auto', timeout: 3000, showType: 'slide', showSpeed: 600, border: 0, buttons: {}, buttonsFocus: 0, loaded: function () { }, submit: function (v, h, f) { return true; }, closed: function () { } };
    $.jBox.languageDefaults = { close: '关闭', ok: '确定', yes: '是', no: '否', cancel: '取消' };

    $.jBox.setDefaults = function (configs) {
        $.jBox.defaults = $.extend({}, $.jBox.defaults, configs.defaults);
        $.jBox.stateDefaults = $.extend({}, $.jBox.stateDefaults, configs.stateDefaults);
        $.jBox.tipDefaults = $.extend({}, $.jBox.tipDefaults, configs.tipDefaults);
        $.jBox.messagerDefaults = $.extend({}, $.jBox.messagerDefaults, configs.messagerDefaults);
        $.jBox.languageDefaults = $.extend({}, $.jBox.languageDefaults, configs.languageDefaults);
    };

    $.jBox.getBox = function () {
        return $('.jbox-body').eq($('.jbox-body').length - 1);
    };
    $.jBox.getIframe = function (jBoxId) {
        var box = (typeof jBoxId == 'string') ? $('#' + jBoxId) : $.jBox.getBox();
        return box.find('#jbox-iframe').get(0);
    };
    $.jBox.getContent = function () {
        return $.jBox.getState().find('.jbox-content').html();
    };
    $.jBox.setContent = function (content) {
        return $.jBox.getState().find('.jbox-content').html(content);
    };
    $.jBox.getState = function (stateNmae) {
        if (stateNmae == undefined) {
            return $.jBox.getBox().find('.jbox-state:visible');
        } else {
            return $.jBox.getBox().find('#jbox-state-' + stateNmae);
        }
    };
    $.jBox.getStateName = function () {
        return $.jBox.getState().attr('id').replace('jbox-state-', '');
    };
    $.jBox.goToState = function (stateName, stateContent) {
        var box = $.jBox.getBox();
        if (box != undefined && box != null) {
            var $next;
            stateName = stateName || false;
            box.find('.jbox-state').slideUp('fast');
            if (typeof stateName == 'string') {
                $next = box.find('#jbox-state-' + stateName);
            } else {
                $next = stateName ? box.find('.jbox-state:visible').next() : box.find('.jbox-state:visible').prev();
            }
            $next.slideDown(350, function () {
                window.setTimeout(function () {
                    $next.find('.jbox-button-focus').focus();
                    if (stateContent != undefined) {
                        $next.find('.jbox-content').html(stateContent);
                    }
                }, 20);
            });
        }
    };
    $.jBox.nextState = function (stateContent) {
        $.jBox.goToState(true, stateContent);
    };
    $.jBox.prevState = function (stateContent) {
        $.jBox.goToState(false, stateContent);
    };
    $.jBox.close = function (token, boxType) {
        token = token || false;
        boxType = boxType || 'body';
        if (typeof token == 'string') {
            $('#' + token).find('.jbox-close').click();
        }
        else {
            var boxs = $('.jbox-' + boxType);
            if (token) {
                for (var i = 0, l = boxs.length; i < l; ++i) {
                    boxs.eq(i).find('.jbox-close').click();
                }
            } else {
                if (boxs.length > 0) {
                    boxs.eq(boxs.length - 1).find('.jbox-close').click();
                }
            }
        }
    };
    $.jBox.open = function (content, title, width, height, options) {
        var defaults = {
            content: content,
            title: title,
            width: width,
            height: height
        };
        options = $.extend({}, defaults, options);
        options = $.extend({}, $.jBox.defaults, options);
        $.jBox(options.content, options);
    };

    /*'none'、'info'、'question'、'success'、'warning'、'error'*/
    $.jBox.prompt = function (content, title, icon, options) {
        var defaults = {
            content: content,
            title: title,
            icon: icon,
            buttons: eval('({ "' + $.jBox.languageDefaults.ok + '": "ok" })')
        };
        options = $.extend({}, defaults, options);
        options = $.extend({}, $.jBox.defaults, options);

        if (options.border < 0) {
            options.border = 0;
        }
        if (options.icon != 'info' && options.icon != 'warning' && options.icon != 'success' && options.icon != 'error' && options.icon != 'question') {
            padding = '';
            options.icon = 'none';
        }
        var top = options.title == undefined ? 10 : 35;
        var minHeight = options.icon == 'none' ? 'height:auto;' : 'min-height:30px;' + (($.browser.msie && parseInt($.browser.version) < 7) ? 'height:auto !important;height:100%;_height:30px;' : 'height:auto;');
        var html = [];
        html.push('html:');
        html.push('<div style="margin:10px;' + minHeight + 'padding-left:' + (options.icon == 'none' ? 0 : 40) + 'px;text-align:left;">');
        html.push('<span class="jbox-icon jbox-icon-' + options.icon + '" style="position:absolute; top:' + (top + options.border) + 'px;left:' + (10 + options.border) + 'px; width:32px; height:32px;"></span>');
        html.push(options.content);
        html.push('</div>');
        options.content = html.join('');
        $.jBox(options.content, options);
    };
    $.jBox.alert = function (content, title, options) {
        $.jBox.prompt(content, title, 'none', options);
    };
    $.jBox.info = function (content, title, options) {
        $.jBox.prompt(content, title, 'info', options);
    };
    $.jBox.success = function (content, title, options) {
        $.jBox.prompt(content, title, 'success', options);
    };
    $.jBox.error = function (content, title, options) {
        $.jBox.prompt(content, title, 'error', options);
    };
    $.jBox.confirm = function (content, title, submit, options) {
        var defaults = {
            buttons: eval('({ "' + $.jBox.languageDefaults.ok + '": "ok", "' + $.jBox.languageDefaults.cancel + '": "cancel" })')
        };
        if (submit != undefined && typeof submit == 'function') {
            defaults.submit = submit;
        } else {
            defaults.submit = function (v, h, f) { return true; };
        }
        options = $.extend({}, defaults, options);
        $.jBox.prompt(content, title, 'question', options);
    };
    $.jBox.warning = function (content, title, submit, options) {
        var defaults = {
            buttons: eval('({ "' + $.jBox.languageDefaults.yes + '": "yes", "' + $.jBox.languageDefaults.no + '": "no", "' + $.jBox.languageDefaults.cancel + '": "cancel" })')
        };
        if (submit != undefined && typeof submit == 'function') {
            defaults.submit = submit;
        } else {
            defaults.submit = function (v, h, f) { return true; };
        }
        options = $.extend({}, defaults, options);
        $.jBox.prompt(content, title, 'warning', options);
    };

    /*'info'、'success'、'warning'、'error'、'loading'*/
    $.jBox.tip = function (content, icon, options) {
        var defaults = {
            content: content,
            icon: icon,
            opacity: 0,
            border: 0,
            showClose: false,
            buttons: {},
            isTip: true
        };
        if (defaults.icon == 'loading') {
            defaults.timeout = 0;
            defaults.opacity = 0.1;
        }
        options = $.extend({}, defaults, options);
        options = $.extend({}, $.jBox.tipDefaults, options);
        options = $.extend({}, $.jBox.defaults, options);

        if (options.timeout < 0) {
            options.timeout = 0;
        }
        if (options.border < 0) {
            options.border = 0;
        }
        if (options.icon != 'info' && options.icon != 'warning' && options.icon != 'success' && options.icon != 'error' && options.icon != 'loading') {
            options.icon = 'info';
        }

        var html = [];
        html.push('html:');
        html.push('<div style="min-height:18px;height:auto;margin:10px;padding-left:30px;padding-top:0px;text-align:left;">');
        html.push('<span class="jbox-icon jbox-icon-' + options.icon + '" style="position:absolute;top:' + (4 + options.border) + 'px;left:' + (4 + options.border) + 'px; width:32px; height:32px;"></span>');
        html.push(options.content);
        html.push('</div>');
        options.content = html.join('');

        if ($('.jbox-tip').length > 0) {
            $(document.body).data('tip', { next: true, options: options });
            $.jBox.closeTip();
        }
        if (options.focusId != undefined) {
            $('#' + options.focusId).focus(); top.$('#' + options.focusId).focus();
        }
        $.jBox(options.content, options);
    };
    $.jBox.closeTip = function () {
        $.jBox.close(false, 'tip');
    };

    $.jBox.messager = function (content, title, timeout, options) {
        $.jBox.closeMessager();
        var defaults = {
            content: content,
            title: title,
            timeout: (timeout == undefined ? $.jBox.messagerDefaults.timeout : timeout),
            opacity: 0,
            showClose: true,
            draggable: false,
            isMessager: true
        };
        options = $.extend({}, defaults, options);
        options = $.extend({}, $.jBox.messagerDefaults, options);
        var tempDefaults = $.extend({}, $.jBox.defaults, {});
        tempDefaults.title = null;
        options = $.extend({}, tempDefaults, options);

        if (options.border < 0) {
            options.border = 0;
        }
        if (options.icon != 'info' && options.icon != 'warning' && options.icon != 'success' && options.icon != 'error' && options.icon != 'question') {
            padding = '';
            options.icon = 'none';
        }
        var top = options.title == undefined ? 10 : 35;
        var minHeight = options.icon == 'none' ? 'height:auto;' : 'min-height:30px;' + (($.browser.msie && parseInt($.browser.version) < 7) ? 'height:auto !important;height:100%;_height:30px;' : 'height:auto;');
        var html = [];
        html.push('html:');
        html.push('<div style="margin:10px;' + minHeight + 'padding-left:' + (options.icon == 'none' ? 0 : 40) + 'px;text-align:left;">');
        html.push('<span class="jbox-icon jbox-icon-' + options.icon + '" style="position:absolute; top:' + (top + options.border) + 'px;left:' + (10 + options.border) + 'px; width:32px; height:32px;"></span>');
        html.push(options.content);
        html.push('</div>');
        options.content = html.join('');
        $.jBox(options.content, options);
    }
    $.jBox.closeMessager = function () {
        $.jBox.close(false, 'messager');
    };

    $.jBox.FadeBoxCount = 0;

    window.jBox = $.jBox;
})(jQuery);
;

/* jBox 全局设置 */
var jBoxConfig = {};

jBoxConfig.defaults = {
    id: null, /* 在页面中的唯一id，如果为null则自动生成随机id,一个id只会显示一个jBox */
    top: '15%', /* 窗口离顶部的距离,可以是百分比或像素(如 '100px') */
    border: 5, /* 窗口的外边框像素大小,必须是0以上的整数 */
    opacity: 0.1, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
    timeout: 0, /* 窗口显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
    showType: 'fade', /* 窗口显示的类型,可选值有:show、fade、slide */
    showSpeed: 'fast', /* 窗口显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
    showIcon: true, /* 是否显示窗口标题的图标，true显示，false不显示，或自定义的CSS样式类名（以为图标为背景） */
    showClose: true, /* 是否显示窗口右上角的关闭按钮 */
    draggable: true, /* 是否可以拖动窗口 */
    dragLimit: true, /* 在可以拖动窗口的情况下，是否限制在可视范围 */
    dragClone: false, /* 在可以拖动窗口的情况下，鼠标按下时窗口是否克隆窗口 */
    persistent: true, /* 在显示隔离层的情况下，点击隔离层时，是否坚持窗口不关闭 */
    showScrolling: true, /* 是否显示浏览的滚动条 */
    ajaxData: {},  /* 在窗口内容使用get:或post:前缀标识的情况下，ajax post的数据，例如：{ id: 1 } 或 "id=1" */
    iframeScrolling: 'auto', /* 在窗口内容使用iframe:前缀标识的情况下，iframe的scrolling属性值，可选值有：'auto'、'yes'、'no' */

    title: 'jBox', /* 窗口的标题 */
    width: 350, /* 窗口的宽度，值为'auto'或表示像素的整数 */
    height: 'auto', /* 窗口的高度，值为'auto'或表示像素的整数 */
    bottomText: '', /* 窗口的按钮左边的内容，当没有按钮时此设置无效 */
    buttons: { '确定': 'ok' }, /* 窗口的按钮 */
    buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
    loaded: function (h) { }, /* 窗口加载完成后执行的函数，需要注意的是，如果是ajax或iframe也是要等加载完http请求才算窗口加载完成，参数h表示窗口内容的jQuery对象 */
    submit: function (v, h, f) { return true; }, /* 点击窗口按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
    closed: function () { } /* 窗口关闭后执行的函数 */
};

jBoxConfig.stateDefaults = {
    content: '', /* 状态的内容，不支持前缀标识 */
    buttons: { '确定': 'ok' }, /* 状态的按钮 */
    buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
    submit: function (v, h, f) { return true; } /* 点击状态按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
};

jBoxConfig.tipDefaults = {
    content: '', /* 提示的内容，不支持前缀标识 */
    icon: 'info', /* 提示的图标，可选值有'info'、'success'、'warning'、'error'、'loading'，默认值为'info'，当为'loading'时，timeout值会被设置为0，表示不会自动关闭。 */
    top: '40%', /* 提示离顶部的距离,可以是百分比或像素(如 '100px') */
    width: 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
    height: 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
    opacity: 0, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
    timeout: 3000, /* 提示显示多少毫秒后自动关闭,必须是大于0的整数 */
    closed: function () { } /* 提示关闭后执行的函数 */
};

jBoxConfig.messagerDefaults = {
    content: '', /* 信息的内容，不支持前缀标识 */
    title: 'jBox', /* 信息的标题 */
    icon: 'none', /* 信息图标，值为'none'时为不显示图标，可选值有'none'、'info'、'question'、'success'、'warning'、'error' */
    width: 350, /* 信息的高度，值为'auto'或表示像素的整数 */
    height: 'auto', /* 信息的高度，值为'auto'或表示像素的整数 */
    timeout: 3000, /* 信息显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
    showType: 'slide', /* 信息显示的类型,可选值有:show、fade、slide */
    showSpeed: 600, /* 信息显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
    border: 0, /* 信息的外边框像素大小,必须是0以上的整数 */
    buttons: {}, /* 信息的按钮 */
    buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
    loaded: function (h) { }, /* 窗口加载完成后执行的函数，参数h表示窗口内容的jQuery对象 */
    submit: function (v, h, f) { return true; }, /* 点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
    closed: function () { } /* 信息关闭后执行的函数 */
};

jBoxConfig.languageDefaults = {
    close: '关闭', /* 窗口右上角关闭按钮提示 */
    ok: '确定', /* $.jBox.prompt() 系列方法的“确定”按钮文字 */
    yes: '是', /* $.jBox.warning() 方法的“是”按钮文字 */
    no: '否', /* $.jBox.warning() 方法的“否”按钮文字 */
    cancel: '取消' /* $.jBox.confirm() 和 $.jBox.warning() 方法的“取消”按钮文字 */
};

$.jBox.setDefaults(jBoxConfig);
;
<!--
/*PCASClass (Class Of Province City Area Selector(JS)) Ver 3.0*\

@ Code By : Yongxiang Cui(333) E-Mail:zhadan007@21cn.com
@ Update��: 2014-02-15 (Ver 3.0.140215)
@ Examples: http://www.popub.net/script/PCASClass.html

\***===== Please keep the above copyright information ======***/
eval(function(PCASClass){var d=PCASClass,p=d[0],k=d[1],c=k.length,a=62,e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)d[e(c)]=k[c]||e(c);k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}(['8 10(){B h=[],b=A.5,i,w=0,v,u=10,g=1P,p=8(s){B n,O,i,s=s||0,k,z,F="u.c,u.P[g[\'Y\']-1],u.I[g[\'Y\']-1][g[\'1O\']-1]".9(",");7(k=s;k<b;k++){6(w&&s==k){k++}7(i=0;i<b-1;i++){g["i"+i]=g["l"+i].1N;6(g["n"+i].5==0){g["i"+i]++}}g["l"+k].5=g["n"+k]?1:0;6(k>0&&g["l"+(k-1)].5==0||g["i"+(k-1)]==0&&g["n"+(k-1)].5>0){1M}z=1L(F[k]);7(i=0;i<z.5;i++){n=O=z[i];6(n==g["n"+k]){O=""}g["l"+k].K.J(H G(n,O));6(g["f"+k]==O){g["l"+k][g["n"+k]?(i+1):i].1K=1J}}}w=1};6(!u.S){(u.S=8(o,e,q){C.1I?o.1H("1G"+e,q):o.1F(e,q,1E)})(X.C,"1D",8(e){6((e||X.1C).1B==1A){1z("W "+u.e+"\\1y 1x 1w 1v 1u 1t(1s)\\1r "+u.d+"\\1q://1p.1o.1n/1m/W.1l\\1k 1j(1i) 1h@1g.1f")}});(8(){u.e="3.0.1e";B j,O,y,T,i,s,a,N,t,o=U.R(1d);6(u.r.1c(o)>0){j=u.r.D(/([a-u][0-V-Z][0-V-Z])/g,8($1){E U.R(1b($1,1a)+19)}),O=j.9(o+o),j=O[0],y=O[1].9("/"),T=O[2];u.d=O[3];7(i=0;i<T.5;i=i+2){j=j.D(M(T.Q(i,1),"g"),T.Q(i+1,1))}7(i=0;i<y.5;i=i+2){j=j.D(M(y[i],"g"),y[i+1])}}18{j=u.r.D(u.d=/\\d+\\d\\-?\\d+/g.L(u.r)||"","")}a=j.9("#");u.c=[],u.P=[],u.I=[];7(i=0;i<a.5;i++){u.I[i]=[];u.P[i]=[];N=a[i].9("$")[1].9("|");7(s=0;s<N.5;s++){t=N[s].9(",");u.P[i][s]=t[0];u.I[i][s]=t.17(1)}u.c[i]=a[i].9("$")[0]}16 u.r})()}7(i=0;i<b;i++){h[i]=["l"+i,"f"+i,"n"+i,"m"+i,"x"+i];v=/([^=,]*)\\=?([^,]*),?(.*)/.L(A[i].15());g[h[i][4]]=0;g[h[i][0]]=C.14(v[1])||C.13(v[1])[0];g[h[i][1]]=g[h[i][3]]=v[2];g[h[i][2]]=v[3];g[h[i][0]].5=0;6(g[h[i][2]]){g[h[i][0]].K.J(H G(g[h[i][2]],""))}6(i<b-1){u.S(g["l"+i],"12",(8(i){E 8(){p(i)}})(i))}}(g.11=8(){7(B i=0;i<b;i++){g["f"+i]=A[i]?A[i]:g["m"+i]}w=0;p()})()}','|||||length|if|for|function|split||||||||||||||||||Data|||||||||arguments|var|document|replace|return||Option|new||add|options|exec|RegExp||||substr|fromCharCode|||String|9A|PCASClass|window|i0||PCAS|SetValue|change|getElementsByName|getElementById|toString|delete|slice|else|9700|32|parseInt|indexOf|26|140215|com|21cn|zhadan007|333|Cui|nYongxiang|html|script|net|popub|www|nhttp|nDataVersion|JS|Selector|Area|City|Province|Of|nClass|alert|113|keyCode|event|keydown|false|addEventListener|on|attachEvent|all|true|selected|eval|continue|selectedIndex|i1|this'.split('|')]))

PCAS.Data="a68$qDIy{{g9PyK1yMg4B@hUSyZcQGhLRyf2R@aysKMa35y5y0yeGSgFGypIJzqDI~dF2O~e8Ie529#cQ5hQ1$qDIy2yyye8Syym5UgK1ya2PyVyhQ1yqECy6PydDPcCNyi84zqDI~~sBL~nNR9#uMie50wHygK1ygK1y]ya5Hs35kGRyoNH]ya5Hs35~hBVX~gIQ[oJ8bPC~iKHdGR~bqLD~i0DhOP~pRQk53~fVSgCT~~aQVhHB~pSH~qDNs72wnUTwg37wgwu4RlxbPCwq0Byq0By_aUIye8SyKyKhU2yg8LcSVjQKyi82~i82~ga69~qET~jD5jQC~qKHQwqETxl02k53<wcyaSFyf2GyVi7T.5u7A~f5M~bBU^qMBqOKwqMB@a1N1ycP90ydOCdOCkGRyqMBqOK~DiAF~f1C~bH9~hT5~kMT~mM1m~Fe4G~qMD~u1ThOP~n~sT2s4I~tBB~g8EbK4~6xqLU1wgK1ygK1yqLU1~D[aT1a1K~gFBm~odI3~a8N~2~g37~e04u4R~~ndDJ~m~d0T~P~D~dE7wjxaFPXw}}}i7T[Pn7D~hTQ3~s1O~eDC3~X0~bPC~b~dEL[hTQB~g9NN~~g1F~g8E~oHT~sKM~bBMqVA~s70~hURwXwdwbkLDe5Jxe9SiRwgK1ygK1ydDVQya17n6Dc89ydDVQ~e9S~e6JaFP~hMPB~dHMa35~nPM~p~eGS~a13aS4~eGSgC1~hURu4R~pS0[dP3kQO^f4R=wbF8gK1ybF8i82yu4Cf37nI1qkGRyf4R=~0o~l~i82~oQ~Ki7T.dEPi7T.c8GcAMi7TnLL_?hM3w]yqFCyhM3~V~aR5~0~k7C~mKV~k6A~bIGgK1~jAA~dBRgBDc7Q.hN6cQGwa8Na1Kwrt51ws0Gxe76cB6wh9Tynyc8M~FP~sTL~[fT3~bCUc7Q.sAKwa15xoJT3wgIV{gDVeAM~6qLD~6eAM~sSI~~fR1[g4B~s1O[aSSwi0D#ucQ6pwdHBe5JyqFAhOPygBBn6DdM9ydHInB5cC6ya13gFB;yg37ByPeDC~g8E~d10iO2~_a60xbH8w{kGRyqN6ynBVyb~cQ5s~niKH~iKHa1K~hRDB~e02O~bH8^lw{kGRyqN6yX~k6U^HhMNw{qN6yHhMN~oR0cDV~dKBjRL~sKM~u7A[cOIaSF~Hq~6m~hKT~hKTB~iBQxg37w{hKT3~[L%~hOP~bxg9Gwg9G{tKTys2G~e5G~bGFjD5~eGSa6T^g37&wgT2h9TygT2kQQ~e02gAV~2sKM~g1G~dGR~cQ6pIJ~kQT~qK1~iKHM~a77a9DxqFCwk7C8yDj9J~a13nBV~s0NbTO~l2J~m7N~m7N~cDVg8E~cPB~s32~n6A[FhRAwhQ1xeGNweGNe5OyXoR0~a5G1~a7V~m2TdNL~6~sBLg~kRQhJS~a5GdG4~dLUdLM~g8E~aFP=~aJBaSF~pxDhKQwdI3Nyg8EhKV~mGO[oR0hKQ~hQ6hPQ~_~hOP~hSA~Y~m~~s6C~F2~nME~hKQ~aEBCws99xbHHgKTwkUNMyfT33~a60[0~D~gGF;~MgSO~dLM~fUL~&~a60R~dBPa35whKQ#aT1nLL_ bAM$bKO2hS5Iw{c7QhHDyjD5lypRNmBHyc9Ru7KIe02vf3K!f3K~2;gIO~P3~6%^b91cQGwyg12Na7DyV@Mf7CyeOqOUbBMkGRya3Ppyc9Ru7KIbGFvc8M~fmBHn8Ug1AmJGbH4fVJ|Wwb6Vi4QyyWfzpS0dOCwm5U@aQVdDP@gCQ@EtKTkVDhKTv4;e02v4;bGFv;~!a6S!mSQvmETj3NIvbSSbT3hKTv[fRIhJ5fVJ|aqEPwkVDhKTykVDhKTe02mGO&vkVDhKTe02mGObHAve8StKT~e5FaA2vcR4g8Ovf3AtKTIvs99;qO9b7ExqOUcPM:wmNOyf7IvaV2gIOvqOUf3K!b39vqOUf3K!vgC9rQ2vWdDTva96>s99hPNfVJ|bKOaA2pPPw7yf3ApR5pGMyEnBVvnE7b5NfjO2ffTT'vqOUaA2g21 vqOUi25!'vs344o3Av4o3Ae02v4o3AbGFvi7ThQESwj3L!Mwf3AGdKBwsLP_m6FwgILx4eC2hVIwDya5Gp~kOGR~W7Ib39vW7I&vW7IbHAvgC9rQ2bHAfVJ|WGdFRtws72ybBFpR0~Q=~bR2N~02~aV5[dFRkbGFmGOb39vdFRkbGFmGO&vdFRkbGFmGObHAvc7NqjD7vKsx0k7R,WGhS5IwEwkVDhKTbGFmGOb39vkVDhKTbGFmGO&vf3ApR5Ivl4Tl^rPT;qO9b7Ek7R,a58qFQhS5IwrPT;hS5IwE4c1Avn7BAIe02vn7BAIbGFvWjFSl32hKTvWjFSl32hKTvcQ6a72dGMvrSIrvhBVrSIevhBVnNPvcPMaA2^E7bT0k7R,E7bT0e02vE7bT0bGFvsLPhRAm6FfVJ#qEPuhL4w2yhL4yyk53cVDyrMTyn7BidKByLyhL4ya5AhQ6yqEP&~e6J~hNHe5F~hHDxqFQw&@dLJyjRyjPKa5HqyfV1sKMRy>yH~jO2f2Re5Jwg4AGe5Jwe50xsD9wrMTyrMTyl77@bAV@1~dM7dM5i7T.xf5MsKMwf5MyhQEyg9Nn6DysKM{f5MsKM~dEQi7T.Ppi7T?gA8Jw@J8yg1A@n68ygA8Ji7T.gJFa6Ti7T?a2LwaQVdDPyfAB0yfABydEPjQKi7T.cwb00xrQ2w_cJGyaV8ycQ62yu7D~a35~aV8wsxnI1Rwl7Lb39y}tL1tKOc94ymGTqELyk7IwMgK1xs1OwyqMDycQ6yPZym72ys1OnLL_.eCC6^qEPwecJGyfT3c9VydDBa9Rye9FHdM9ycQ6qyqEP~iKBcJGxk7KrQ2wbF81qy0o1yhQO~k7K^rMTdM9wrOIyPyrMTdM9~K~5c8Q~pGVaSHwe8Spxg9PwbF8cJGy{g9P~e8M~bSSbT3hKTe02mGOnLL_.kS4waV8BxnK7n62<wqFQ@cykS4ym81&~e8M5~0#Y;uHg21waSFydEP{g9Pya58qJFym8Rc89ybF8yaTO~a3P1wgT2gHDw=eNSxY;w5qLDyiC9yn2LnI1yKi7TyFY~o7RwgK2jQKwn1EGwkNCMxc7NwrMTyrMTygM4gHD~a96ai7T.aS8a2NdM9wbF8qEPxqEPBw@yK~qEP^aQw5ya58qJFyaQ~qD5~gGF~gL1Rws72xewhRDyByf5MgCQ~sBIdD3~Heg9PtLO.DxgCQpwyb39qO9`:nLL_.HdM9~a4Q~f4IaBLxewhQAyspR5~agT2~hQAwxe8IqELg9PtLO+e8IYwc8Qa88wfS2QwjGEg21wa5Hw2whK6P~c8Q9#u7Duki84wqJFSydLJyqJFcPIyf2RygCQysTLcB6ybKOGyE{aDPG~fULhBV~dEQ~4eC2~gA4G~a~e8IdGR~bF8wdHMeFJwa5Ge2KxuBCuBCkwjye8M]yrMTrP7yg0Ug0UJydF87cGMykMQq@gL1S:ffTTy~aDP~hOCgC1~jPK~dF8oNH~!~!~f7Ol~pEJxu1Twu1TaTSyeJE@i8GqJFygM4gHDyqyu6N@u1T~o3A;wdF2xu40dLJwbHDye01aTOy@0y@0@nHP~m81i84^bF8u29wdHI@dM9yc7NfUL1ydDP@s72pQ0~bF7pH6~dDPP~sSI^e52wnI4c8Qyb00ypE5mNTq0Bym5UdLJybH8ymL3~mL3B~;jQK~gBOaABInLL_?a96g21wa96g21ydLGybF7cSPy;ymFSdO2yVymDAJy>dKBya5GnI1yWCyhK0g0Mye22dM9yWa96dM9ym5Ug1Rya16jPKdM9yc15nC7~rMTb5NxaCFgA4:wbHDyb39qFNysNAyqN6ygK2~gK2%~hK0p~f5MqFO~bH8wdF8rQ2xa0V1w0ygIV@n90qyb6VT^j3Ta2Lwyg1Ayj2DhHDyy~;R~m81n68w;wwl32gODxu7Dwj2DqD5yd85~qH6!~dBLbIG~wa5GqFQhJSxm81Qw;yg9NcRA~G~VaT4~e52~g1A3~m81gOD~fwmL3waA2x0dM9cACybKOjDN~cJG~i9S9#a16$qDIyrhS2yeDChJ3yHysBLyg4As2Sys0Kyo4LRygC4hS2ys0HoJ8ydDP@c15XyhS2y>@gCQyVhS2ycR5pQ0zqDI~dP3g1A9#n7Bua68wjD06yl02i0Aye8MqMMyu9FgSOyhS2RygHIs9Qys84n6D1yyaS9bH4yi633ybi0FzfVSrPTwdP3yHycJKyrPT@eNS@i848ys2GwdDO0xeDCwu9FgSOyOypQQhK6yl@rNO@K~hLN~kBU~hKUwqMFxe2KwcQ5yrLRgSOyf1McL1cICyy6qFNyi63w>cBNxn7Bwo3Aa1KybIG&yk8K{cVDn7BybIGye2KiTRwe9Sicwg12wcQ6a7FxawdP3%ycs0Kyay~cSU~bIBwcSUk57wZxqFQOcwqFQOyhS2yypRVgT2~~iJ8O~iJ8^i0AwPyi0Ayi0As2GyPhS2yhTR3~hQ6hOP~k8Dk9L~>8^k7Cwa698yk7CNybN93~i84~s1O~dH0~e8M8~1wKxf48wnLyqLJyNydDPe5G~a86eCTwbqMAxswa68RyhU2ya2LeDEya2Lwf48&wbG1dELxhOCwLybcycVOcICy0QwsBIwhOC0xdERqETwdER{dERpK7yhM9~hNJ~hNJhQ69#hRLugC9wa16{a17{e4Eyf8DcL1y8yi84ynI3@aBLgC9ygJCe5C~i0F~e8M=wdF8wDxhNUwg7Lyyya7DysyqPQypJT~~aBLcVMweR4JwcR5Qxi25wu4R{i4QyjOByhPQcQG~Fc15~~n79~fT3f1C~hOCsKM~jJQwgPxc150w8ykUShQEyc15bT0~k7C~w8wgJCmx8wbIG0yhRGy=P~H0~Y^m790wpT6{gGBgK1ya16o3Qy5~pGKg64wdR6x>]wd4M{>y6a35~hS2~kNC~GJwa35WwwFe6JxoJUwgGB{oJUye2K~e8SQ~i2K~xn1RwXyg4As2SydMD~dR6hNJ^1wgPEyrdM5yq0BgK1yjD5jEB~a15Z~cQ51~a7LdJ1~i25dM9wDxa2P3wnEENyVjQC~m9LO~qIU5~gCQ~O2~e52aQV~g4BjSE.l#eEPubH4mM1wjKIye5Cyo8S@b91yHK~mM1~mM1~e5C~dVU8xn5O8wrRO8ye97yu1Sya15@n5O8~m2T5~L~fVSa2M^o58cFSwq8yo58@kULa9Myi0Aa16yeGSqFO~a5G~c8Ms^i0AwayjQCie6HypHUis72yaS7aS8@iBKs72yb001^CsD9wn6D@s84@bBMg9NyeBFhSU~bI7~2^i0AwgBOs72yk8K@iN4@iF5J^rNOLwrNOdDK@j8Aq@qN6yrNOL^e52wqFAyoUUydDOkUSyeGS~gDQ~iBO~cQ68~dERgCQ~g9N~dMF~gJCxrwdKBJyr@eEPyhBL~a9D~u7R~kQTZ^i6TwjH1jH6ypIBygC1~aS4gPE~XqFO~b00~cQ5Hwg1AaR5xs1OwsL9ysL9ysL9lyDl~cQ62~s1O~sL9a16~jR8sTIxdERwcF3gK1ykGS~nI3~iKHjM3~hNJ^aS9w>yoNHydGR~s99qMD~n1E[>dG4~s99^a6FwpIB{hTT~nLL[TqDN^hJSwpQHhJSyn0F~M1~V^dDVwdDVyqNAJ~n=~hOQ~m85J~fV8=~d#kTBe8MukTBwu9FgSOy1ya7F@CdIQyg37ys0PaEB~qFQ~`B~s0PP~FhOC~iC9~kTBPwHgxbE2ZweHPg1AyhM3y8Sys72mDAybH8ymFGznD2jQCwbDUyhUHynBG{kUSdKRya7Li2K^a15g1AwgL1b1Jya15aQVyg1AJ~PhQT~Q~jQC~dI0J~j~dH2g~hOC~e8M~FxlwtM0{KhOPyhPNylcyeNS~J~Fg21~=Q~>Z~Mj8Awg37wxiAFwn5J{fT3yOs90~iAFhS2~pFB~HhOC~~sBI~2~]~xwe8IysKM5~hS2[aR5hOP~gCQJ~fQR2~qMH6w6cQJwe8MjOBwe8MxdM5w`yHhIS~FX~a16gC9~6~qFQ[iAFx=wnR5{s9QhS2~_jQC~dJB~dGR~bK4~gFKnBV~kTBwkTBu9A#u5w8y8yVOpIDyi4QSyV8y5~e8M~a35~qFNpQ0^g4B=sw5yjFS@hSAgKT~gxnH9mwByi3KynEEn6D~a16gHJ~n62J^a3Pwe5C@hRGya3P~6~aGA3~FaGA~=~g1Rq~N5~8R~eC9hOP~jJQ5waSDVxaBLwi1P3yb12dDO^u4CiC9wg948yaBL~pQHJxpRVwl7SpPTye6JypRV~aFTK~aBL~a16j6L~dP3a35~qFO~~X~aS4~N~a5AN~0d~a9M5~dGNW~M[jJQ>xYwYyVpyY~Y3~dNT~e4E~FK~hOC2~qIU%~a13~kTB~F~a5HaT4xdDOg21woKTycR5~a13qCP~a16b~dDOK~sBI~rNOu9F~Kwh1RgHDwbxf5MwD%y[u7A%~K~dP3a6T~g~dDOr~>J~pR0J~m~n5^a16sSIwaFTya16sSI~nK~jD5~rN1~h26dOC~e97~aBLe4E~qQD~a13e4G~d4MB~=0#uhRAwbD2a17y&ygVCnC7ycQ5gK1ybD2{HPys2G~hRA~bR2~l7Sa1KxV<wyyr<ydOU@gBAhM3yymOIwbCFcM4we62wnEDxhV0bBMwhV0%ye9Se5JybBM@DhV0ybK4gBDygJF1~bV~hKUB^gDVe50w&ynTN{dN0{1aQRe50ya69yi7HxnI1wnI1yRycE2T~ThQ1~nsSI^iNR1wn5PmBKykTB@j3RynED@H<~RwnEDwnEDwnO8nEDwf7NqFOwgHIs9QwxiB9cB6wiB9{dFEa69ycB6qycRAfT3yDg9C~5g~VwpGKwdGRaR5wa1KwbdF2w5qLDxhRAwa8N{aRIyeEA~tKO1~>m~c15kS1~hKIa16~hNJ3~gKT~g8Es1OwqMLxhOCwhOC@dMDdMFy~~hOCwmM1xd0TwjEBmFSyfT3k4NwnBVf1Cwa4Fxg01iS3wcydLM@a5GnEE~nDE^nEDn5OwnED{rLUzDhKUwG@`e50yyhKU~qOB[hKU3~n79~pQL~qLD~nDE~nLLs2G~DhM9^=w={L~hQ1~e52O~DqLD~uBC~p~cPBhQ1~6[gLwkULxmJ6w5e5OypIJ~nDK~n98~E~aTS~bbPC~DPxi84wi84{eNShHD~aFT~fVSgNV~hMQQ~bBM0~qML^nFBhOPwj3Ta2Lyg8L~bBH~f1C6~e04qVA~qNF[qP0[Xs4I~g1A9#uqNDw&pya58a0VylDTc7Qy>3ya16oJJyeNShRAy&j3R~e05a35wnC1wdF2wqNDwk4NdGTxe8SdGTwa69ysKMc7Qyu9FgSOykULjD71y>g1AygBQ~apEK~dH5hHB~e8SdGT~GmGV^hPNwmGT{e01yiH9c7QyhU3yYTyhPNydBRhQ1~~gIQ%~dS5~hJP~dDO~hPN~a96%~aIVe14xsKIw]ybC7yMyi3NydDPK~bGI~tKT~qNB~n1QrLUwhJPxwfT3dOCyaSFyhEJNyy~hK0s2G~i7D~aT1r~;xu40cMTwu40@{hV3i84yhRM~hV3^mwm5UfVJybC7i84yb00lyj43qVAym~nEJc15~p~e8IhQ1~dGTa1K~HcDV~bC7qD5wqD59xiQ2aBOwoVVfQQy&l7LyCgBDyyaGA6~bBMj2D~6s3R~i25~hKTwdBRxiGAw]yPK~g~n8V~1b39~iGA^pEK5wtBBNypEK5~qPUL~oR0[kULwHnJNxiABwBhJ3yqOQ{bG8Lyn1Q~DsL9^a15ZdNTw8i84yi1DhJS~s3H~bBUhHB~a35CwiKHdDPxwdDN{bC3ybG8~fUL[dNT~s~aT1m~hV1%~kQQfVJ~bPC~qVA~gJCgFB~qLFxbR2a1KwgKTc89ykBUyhHDgAV~kBU~L~gFK[o3Q[cPBqLD~FxaFTwhR5ygK1y`~aR5~~bR2[c8McV7~iBU%~i0Ai84~eKB^bK4Rw%hJ3yf4IhLR~]~bR23~hL4a1K~qOK[i0A~cQ6e6J~u4RqLD~sKLxt4NCe5Jwt4R{~a16nPT~n12~hBV~kKA~hN8~hJP~qIU~nPT^k8Tk8GqDI9m63oJ8fQRbAMb1E,hRAB#8u6hJ5wdMKyhJ5ykJMRyhJ5y65yV@hQ6@8yhJ5ynPTjQKycPByrs2UyhQEzrMwrMcycJQ@a17s32yrMT@~aUIxbATcICwn91lE9ye9Si4QyqO3~qO3~l8L~l8LJ~f2R~a2LRxdDO5wLya99idLJyiMLaTNyj93a69ycQJLyqFO~0~l09eBE~Hc9Ri.a5GdOCc9Ri.dDONweBFwgDPxoR0woR0{h16{oR0yiAF~pIJ[aFPe6J~mGTRwgDVwdDOxqOUwgKTq8y]dELyqOUznB2ZwdDPyfD3b0Sya68~jhP7~rLRkS1xdBPePRwdBPydBP5~eLR~OgM2~e5Gws32whJ5%xnB2wj}nB2yaS8~k7DT~L~MsTIwhQ68wgCQi77xraT4wryc7UsNA~m5U~`jQC~n8D~hRS3~nSEg21~rgL1~u6Nw6l4GxbMKwbMKyc15tKO~a[dP3~a~pS0cMTxs5Bwg8QNys5B~n3xeK5fUPc9RiU+eK5fUPwT%we8McV7~4~dDVeK5~bMKK~gC1b00~u40dOC^k8Tk8GqDI9m63oJ8fQRbAMb1E,a7LgIVwiBOwcQ5ZwkRQaTOgEI;bAM#8uHjwn5LnN5ycQ5eEVydMFu5Fye8SkTBys84n6Dyg9N{Hj~m~hRBxgI6hQEwnCJcJKyn62hVQyMdOCycQ5aQVygI6hQE~fQK~nAIL~iLAL~qUGLxi3KiC9ws848ydMFcJKyi3KiC9~i3KmwsGIxoJTwjFSg3Iys6TdOCyMu9FynMKi3KydMFyoJT~oJT~oJT~oJT~kQT~mHEwe2KxqMHwbF8PykS1ycJGyqMH~qMH~qMH~oc7Q~hPQR~m81~~hC1U.6aT4xdMFwdMFgSOyOJybHN@dMF~]dEL~i3Ks2G~~hK4`wDi3Kxe2K=w6Lyu9A{m~hJ5dGR~iE3~DiE3~gIVB~MZ~hQ1xe9SijR8wFXy6LByeR4T~gJDgP9^k76wpR0ypS7@~gIV~Q~hL1xqOGw8yn7Ba7LygIU~dDOl7S~F0~c15kUQ~D6~hJP[gIU~a6T~pR00xFws8ILyaUJ3i85ykQT~~bF8j38~qJF~F~qFO~nNP~jQC~]jKI?eGSQwu40{&fUL~hL1L~qECJ~i52hS2~a9MbH8~u6NU.g2VaDJ.n6JaDJ.sBIUaDJ.aqJFaDJ.hQ6xd10e5Hwd10g1RybF8dOC~Q~aUJ3whTRBxi3Kc9RiU+YsTIwhOKJ~b00b0C~n6DcDV~aFPsBI~_a14~FsKM~9#nunwnBGi4QypT6kUSyjFSycQ5yeOyrcFGyjS6kUMyn6DNyjynHPdLJycLQwa7AQxsGIaSFw6yhR4yg8EycV70~a6TQ~mETB~a4FBjKI.K~g5ws70xi0DcAFw`8ykTBjQCy@dDPydLJyk7CjQCzjFSwsTLhQEyfTJZy>i4QzhJHcQGw8y>yiFSyiCAyiCAyiD0yiEF^aBNwkT1{ysKM=ya153ybg1AzZwnO8yya9My1we8Swu40weK5xi3NwpS0cBAys9Q@cBTcQGyu6Nl7SyqIUJ~eDCs0N~e75ws8JwbIG%xn8UbH9wn8Uyn8UcyjQHe~bwQwaFTdDOxmL3e52wl8Byu9A8yn~eGSs72~dGTe8S~=e52~boSTwc7Na9MxeNSweNS{eNSybBM`~eNS~Z^gL1wgL1ygL19ycFG~KsKM~a5G]~qFO~nR5dM9~0xhJHdIQw{K~s32~s32KxBwB{lQ7>~%~qFQ~2~B^w{~~g21xPqFOwP{PyaBNaT4~~qFQcOAjKI.qFQjKI.n8D=wqFQxnDQx&xiCAwi3KgK1yiCAysSI^fG9wgTH{fG9yfG9~eNSgC1~g4AxOhSAwO{0~qMT~O~`X#ncOA'bAM$w0yVkUSyymcJKyn4Be52yqLHy6u1V~o~C~a16;~dEQ~h26^gGFw&ytKOdOCygGFygGFygGF~gGF[u4RdG4~oD9~oD93U.a15aDJ?gIU;wkUSdOCybFSeC5ypJT@a0Vg1Rys6T@DgIUyg9G~iKH%~aS4~0~FkTB~iJ8~mNObH0.pR0B~g~nBGhS2~eK9jKI?gM3wa13kUSyHhQEycA5yn79gM3~o00~nLL~dLDJxw{rOIyrMTcybH4hS2^s2EcwcRys2E{a16eHP~0xrM2wrM2yrM2yiKH~hS2^pQHcwcycyoSVcJKy~gIUxjD5;wjD5ykTBm8HydEL~s32%~bBMe~0a1M~hQTxk4Qn4EwbGFyjQC~jQC~gDO~=aFP~sBI~qLVcBT~aV8O~ga1M~jQC;~;~o;bH0?pQMwaS7hC1yg29~rLR~dF8%jKI?hJSw>ya2L~cQ5dO4~b00~G~`a87aC8.jEBhFN.4CjKI.NjKI.QjKI.dDOxgC1dEQw0dEQyeGN[pJT~6dDV~>kUSjKI.bH4xdP3e02wyf4Im81~g1A~~~cQ5lB5~b09kS1#uRwkUSn8Dy]yjIO@mDAGza15/wqDIza15jwqDIzk8Tk8GqDI9m63oJ8fQRbAMb1E,a5Gf93wjIOwaP7wfT35wa13wfULwX~dKB5~iD0qF4~Db~eju7A.5u7A.gu7A.L3u7A.aFPa69u7AU.jIO&u7AU'9#qV9e52$qDIya13yhU6Lyi1P&yi1TRyyjcC6cBPya3PcBTydMKykLMylU2ypUFyi1Py4yu7GyHdGRyhQ1ybH4%yF%y%zqDI~iCO~rNOgKT~nBV5~jM3~gKT~R~KN~cE7~6o~eFS~e8S~O~cR5n4U~e07~e07J~MgGDc9Ri.kUSc9RiU.qR5c9RiU.eC93Uc9Ri'9#c7N%uf1CNwrQ2yVmD6y>j3Ny6aEByf1C]ylt4RyVeyNyi25y>cGU~bF8hQT~qO7~qLD~nME~hQ1~NcICweC9wqLNdOVwdP3xn06pPTwn06hQTa5HypPTa5HyyhMRi85ynBV~dF8sKM^fOSgDPn6Dwyya6T2ylKFg1F~k7CqEL^hOKwym6FJyCiC9yhOK~bH4~bFLF~_nQM^=wfV8y&~`~nhJ5wa6SqLTwm8Hl8Lxm8HwhU6{i2Ka7Lya151~k7Ca69~~gLFiCO~%mD8.6~hMLxnaQVwTyg29Qyg9PcQ5yg0Mn79~V%~b3Ds0T~n79J^qIUwn2L@dJ1ynO8J~dH0hQ6~n8D^aT1w&y0yd0TqFO~pR0&~o5^gw&yji4Qya5GagK1y>Ryj59a2M~a5HkHG~cQL~hLC%~dO4qELh.CqELh.dO4k95xaR1wsKMe52ybcC6yc15LyqO4~nI1~nO8~a86s33~aR1~s12&xk95wcBTya6TdGR~eC9~hQ6s71~a2LgOD~VkRQ^dDOdEQwmFSdJByJydDOdEQ~~H~b~jFL~lBSqFQ~0fT3~dJB^nwnyb39rP7ydMFhJS~6mNO~qMN3~]nO1xfwa%yf%ydDVhJ5~e8S~l8L~i1S~a13Bxs71ws84{bH9@nC1m7B~hJ5B~MgN5~cQ5aS4~n62~dDP0^4&w4yeK5ya~~5^pR0ws6TydMF~gn0F~lCSxEcBPnVBmD8+hKI%~jH2~n8U~gCQiBK~a3PdG4hLR~>%~dHB>~u7D3~Ce6J~cO0cJK~EcBP~n81k7I~m5Up^jPKdBOnVB+e6JX~hOKX~a2L4~a3P~s71~qJFdBM~iL5s99~jPKdBO~~=gIO~ejD5~Mi1S~n4Ef~jH2cJK~4cJK~m[l2N[eDJnBV^aV5h+5wgA4SnVB.k7CB~=5~a9MjH2~a9M~~g4AgIO~tf7I~>~g29oV5~bTO=~aTH~pT6~jPKhPN~mDAcVD~s8JhNU9#pQHupQHwg1AyOdM5yn6DJyWeBFyeOyoUU8ye8S~eKBiOP~aGAfT3~PsxaS9k7K3wrLR@aS9gDPIy3[k7K^qKHa35wm5Un6DdLJyhJ3%yqKHa35~gJCgLF~m81~hBV~qJFk9Ra7TaC8U.b5T%a7TaC8U.b00aT4~i30iC9~aBLe52~a3S3~pS03wa6TeGSxsKMwkUSycBP~g4AX~staDPU.aSFdM9taDPU.lQ7OUtaDP?hFHn4Uwa0Vg1RaSFyfUL~u7G~>j~m73>~m6Fs79~d0Thc7QU.pS7l7S^rNOa6TwkM3ya13@R~jD5dJBaDJ.Ms1T~eHP~bCCc9RiU.=~hMRc9Ri.gCQgIVU?u7GtaDPU+0a35w0a6T~g4A~g4Go~pPQK~g9NpHR~aT8a64~^u7GUaDJ+b0BSwr~fUPkV5~a15l3J~sqFO~dLDe05~cQ5gGD~rQ2dJB~b3D~1~u7A~gTH~a7A~s8J~u6N~a2LdG4^u7GtaDPU+Nb8SwkTBlwnBGhNU~pQHX~jOA~j88~cJK~`jQK~HsKM~S~eNS3~a15N3'9#Oug12g1Awa5G]yk7KydDKi1Ty@%ybJ4pPTyg37~dF8hHD~dDOn4B~M;h.dS5g1A~kT0b5PhU.dGNjQKc7Qh.xg8EsBIwu5Eu5RyC~s32n4B~e14dDJ~`~dF8B~a9MhOP~hMQk76~dDVd0TxjD5Jwm5UcJGy%~iD0~a~]~g1FZ~dO4h.haLV.aQVkAhaLV?aFPwoyfUPjQK~mSQaUE~L~5^g29awg29ytKTjQK~e03i~k7ChQ1~aSF~FbT0~m81~ss70~hn4B~d0TaFT~3dF8^a2Pw_{jD5m6F.FmNO~]cC6~nLJh?g4AhQDweHPn91yhQDkAh.cM4kA.g4Bh.g4BpIJaLVh.shL1hkA7kRO.kAh.dBRqFQaLV7kROaC0.iDOhM37kRO.k7RaC0?DhM3wDmFGyb00e52~O~F=~se6J~bF87kROaC0tg9JaLV.mIRCaLVaC0.hM3BaC0?gRMs70h+gRMs70wbF8gFB~j3RX~]~cVM~cVM~Fa6T~aQVpH7~6X~kT0K^m5UkAh+a26g03we8SqFOwnLLn06weA1b7EwdJBqELU.e8M3~MdJB~hOK~aQV~m5U~>UjKIaLV.m8Rg21~RjKI?fT3cOAU+fT3wkHM~jSG~u6NgHJcBT~CaSF~a1K~n~dF8^bF8j34m6FaLV+g4BhQ6wb7C~b7CmR6^jH2e+jH2wiAQiFQh.kS1O~dEQ%~eA1i1T~hU3h.dV9hc7Q.F~O~hQDB~b3D%~u40e52^=dDBaLVg4BsL3+jJQa2Pwn5EwgKT~k74~s33%^eHEaL4aOF+hOK3~kTBpPT~pPTj88eHE.GcC6eg4AlKF?qG6e52nVB+sTLgIOS7~=rM2~m8GaL4aOF'9#nVB bAM$7nI4waSFy;bK4~eBFs70~AgA4~g8E3~cH2=e52~fdBO~cM4l8Le01bBT^5NcACy5N~f~pPToV5~lKNWuBC~a0TV~dFRs71~aS7dER~e02pPT~n5Ee6J~hPNo~qELcBP^cACya2V~f3Ac76~pPTc1A~gJDg01~jIOm7F~g8EgCQ~fE6mDA~hPNf3A~b5SgG1~oq~rPLqLV~hS6bBTq^g01bSSb1LcACyg01bSSb1LwgA4;~dBO~Xg01~nI4qG2~7dBO~g0Ua6T~pHUaZ~eg9J~a6Tt~e6JC~Xm7F~a8E4~/~Yo~mIU7gA4~nI4c1A~dLJ4^qLVg8EcACyqLVg8E~c15u7A~hFGcSU~mIUnBV~cPM~jQFf3A~lPU~jG9f14~4V~AjDN~bF88^EScACyg4AG~gA9f~c4I~g01c9R~sC5Y~fQLb1L~fE6b80^;n5PCAS.Cy;n5P~e01tf~lKF;~cM4mQD~hNUdF2~dFRs51~g9J9#s3Huw{kLD;ynEE8yiJQgK1ygA6cQAys6TcJGys1An4ByDiCOyHynNPjQC~bK4n0F~f2J~bL^rNO%wjD7k76ybCC1ymGSydDObHN^dDPu1Twi29i84y>1ys34a7Fyb00mFG~dLC~f4IsNA~k95~s33~bAV~u5Ri2K~b00~cQ6e^bMKwl02NygC4Lyi29{a15p~hOQ~a4Q~kQOl~FdGR~eC8~H6~g08qLD~i0FQ~6b5R~0xi29wDi29y]~iCOaSF~nBG~bH4~iD0[nME[e3~dF8~sG5w]s2Gxe8IwdDPcJGye8IH~e8I%~qH~cJQ~eFJa2L~bIGpSJ~jPKl~dF8~hPN%~dDO%~r~rL^hJ5&whJ51yqND~c8M~hP7~m~b75~eAM~jS1~s4~jRLcBP~aBNcC6^gT2;wgT2ykRQgA4~e5OpIJ~h26~sBIqEL~XqEL~m81=~lKFmOU~aCF~bIGcHT~PhU3~qhQE^e6JwhJ5i84yhJ5s2G~Ml~s3H~lQ7~dLMk57~T~scC6~g08~e^bR2hPNwbR2yhPN~a2Lb00~bR2~~s~gFQ39#jPKmKVuGwaSFya0VSyc8Myym5U_yFk4N~k57G~gT2&^c15dO6aSFx>5w>%yF5^erOIwerOIy%ysBIqFO~a9M~g4BhOC^cQ53wl02yu62l0ByP3~l02~jPKpIJ~6~e9Si%c7Q?6d0TwaV5yhHDb80~_hS6~cQ5kRPnVB?e9SfDIwjPKymKVoNHc8M.hHDg~DhOP~b1~a2L^aV5wdP2dNEyhOQ%~iKH1~dP3aFT~]a69~e50hS6~sBL^qRElwmKVy>cJG~jNO~mKVnLL_.E!cJQknI4!.jD5ZwfS2iR8xe52wdOCye52[jEB~]hJS~bH43~hBV~~sp^XwXyai29~s33~i29B~DhQA~iAF~dMJ^s33w6Nyf1C~fT3~dDH5~e6J~2~kQO~eEP~a20eBF^DcPBc7Q+DcPBwDcPB~e6Jg~FsBI~n~2fQR~m.l0BMaFPmfLE7?jPKnVB+bH4aBOwDiC9~bBFA~n1Rg8E~qG9qO4~jDNg8E~kL8g8E~cPB9#Vuwy&yyyac7Qc9R.i3R&~i3RB^wgNy~hHD2c7Qc9R.a5Eb65c9R.Qoc7Q.eE6QfLE7?nVB+ZBc7Q.kQTqFQ~g3B~b1MdFR^rnVB+bH8a6T~dHIf3A~hOPe5F~nLL_?nVB+aSD2~bH8=~pQH=~0~pQH^gDOhPNnVB+jDNhKT~jG9jDN~jPK=~fg01~a31hMN~jDNcPM^jD5gHDnVB+jD5gHDwgAUcPM~l0CcPM~hMNcPM~c76pI2~g8Eu6NnED^nLL_nVB+gIOgA4w=a80kwWG~NG~cQ5dON9#cPBc7Q'bAM$rOI%w0e52ycPBy>b00yF~pQMG~iKH6xMc2Gw6RyeNSaTOy`^bIGeFSwTaym5UdGMcHTyk7ChJS~bH8eEV~VrNOdNTxc8MpwpyY~o=~hOQB~eC9^&bC7wjcBTcQGy&~p9#jT2m8GbIQ bAM$WtKTgA4uBCwcQ5@jaDP4!y}3kO4hLRycQGdKByfcAU{lKFyWtKTgA4uBC^!7jDNaDPwj88qy!7jDNaDPyekMDi85yWkUQzbHCtKTjS6cACybHCtKTjS6wqQBbT0~f3K!qH6^kdF2cACykdF2w4ScC0knI4! ~a96bIQ^5Yc7Q+5Yws1Oe6JwbKOc8QcMT~jDNm6F:~cR31~YgA4nI4~gA4cDEknI4! ^bBMcJG7nLL_ ,bBMgwE7RwlMQ~i25l^4sGFqO9gRQnLL_ ,e5Fb7EwqCA1~dH5j4T~n81mD8~a1GgA7~iP5mH2c7Q.2sBL~2kJH~bBM8^E!n7BcACyE!n7Bwi25dER~e5FqC2~js71~2~f7O[Wa6S~EjO2fFC~gGBcC6^!dBOb7En7BgGB!dBO ,Ec8Qa6SwE!s4I~EbH4cR3~WeKC^bSSa6ScACybSSa6SwjTBs30~jTBb7E~n8DYj~hOPg4A~nDAqC2~bGI[u62k7IfFC~dMFg4A8~aAPe14~4gRM~cJGa6Se5Fe4EcJGY! ^2jQCcACy2jQCw2jQC~cM4jD5~k6A~hPNhS2~lBIb7E~a5AjQC~hHDK^a96j4TknI4! ,a96wcRAdKBwa96~dFRtgG1rPTaAB ~s99[e05jRL~B~g29n7B~I!:~Ab7E!^cJGcACycJGwWn7BwsLPfRB~ji4Q~f3KS~oNHhHD~2t!pRNnLL_ ^Eb7EhOCcACyEb7EhOCwthQ1~dF8nSG~kTB~k4~V~YgA4a2V~MqwE7wc8QgA4n1E!wa5Gii1SwdKBwrMTZaSF#sTLcIb27oJ8fQRbAM$sTLc,sTLcIb27oJ8fQRbAM#iEFZIb27oJ8fQRbAM$iEFZ,iEFZIb27oJ8fQRbAM#1i4Qu1w&hBVybH8y&@gCQ@ya13]yaFTa35ycO7;yf5HyaT18ycyfT3zbs70w0yb39>yn65s71yk7CcFHyu9F@fVJhQ1yb39sya15hHDye02nI1ygRSgLFydHBczcGMowa6Tj2DyaFTa35y&hBVy&@gyg5Ig5Iya0VcIHz1&w&yyyyydKBydKBydKBz1w&yyyyyzl8LwyysTLzc15a35wyz~19(gCRgK1),dDOG9(dDOG),l8L9(l8L),gIVc899(gIVc89),UgHJ9(UgHJ),1&9(Kp),eCCQ9(eCCQ),f5H9(f5H),c15a359(cQ6aFP),O;9(fTJaS9),19(nI1),bs709(b00),dJB9(dJB),19(1),n6DnEE9(n6DnEE),iDA89(CaS8)#aSIdCV$/hQE,EdF8hJJ,4;,dBRb5S7d,a19a2L,fT3nED,m91jQK,cJQhS2q0B:,bCCe62,bCCe62A/,a96g9J,a967!,g01gA8,m62g02,g9PtLO,kVDd0TI,mGTf9P,Ca7VcQ7,u7A4d85,CgC1/,a81n4Eb1J,nLL_,AhN6,Eg8O,4cGM:cC2,4b7E:cC2,nGEeD7dEQ,jIE7aAB,b5ScBT,:SGbBT,bFLT/,hOCd,gG8cFGdG4,c9RmIFaSI,EmJGqR7,pT6,a3RZ,sG5d,&d,&dsTLc,&diEFZ,&d1i4Q|sBQhQE,EbF6T/,bO17,bD0SIS/,hNH`mE0u1R,b5SqLVTmE0<()(7:e1HC:),pPP,bBMnA4jO2m6F,tcGMm6FhNHlPU,toqG6,bSSu62o,b5SqLVTmE0<()(c9V!tKT:),aBNeDJoVE,&sBQ,a39eDJ,kVDfK5`,b1MgDO,YtfFC,cEVbF6,cEVcJQaF0hFG/,pS0qJFaVSaT1/,b5SnO8,aT4hFG/,b5Sm6F,aVSaT1/,sBQ,aVSaT1/hFGm79,kVDIqG6jO2,mMBA/,nEDlPUf3K,ThFGS/,ThFG/,Cfb5S:b5S,C7m8G,CS,hFNScJGA/,hFNShIU:,fK5hPNbO1,nE7gJDhFG!,Ag01,Ag01T/,jRLAg0M<,bBUg0Mf,cJQaT1b5S,cJQn18,cJQ7Tg0U,lPUCS,n7Ba2L,:d0TcO7G,cC2gJDA/,c9VpS0b7Ef8R,cPMbO1,l4TA:,We4Ef,f3Aa96,pRQhFG/,hQ14tsG2,m6FlKFhFG/,qG6f14b5S/,gJDm7L4,Cm62I<,c9VcPMmDA2g4A;hFG|hA3hQE,E4A/,qJF,cS1cACT,hFGTg0I,aFPb5ST/,fCJ!,a2Lu62,n68G,hNHd,=d,k8Gt`s2S(n8D),e18mR6,b94j3LT,aUC<,j2DG,ePBT,b1JfQBfS2cO7k4N,:hPNa9C!,bBUgOAcHT,CmIFa7I,fK5m6FbO1,nCJG,fA6d0T,hNUG,nJTnH0j3L,CaSIsKR,`CA/,:7cQ7,c9VCb5NpGM,jG9j3L,jJQaSK,jJQcO7,n8Dd,kVD`cAC/,:hPNfT3A/,gMHnKUaT4,hNU:A/2cJQbO1m8GqLV,aF0`:mJGqM2,/mDAA/aSD2d,eaF0`:aSD2d,gIOtKTY/aSD2d,knI4!:cC2aSD2d,YY:cC2aSD2d,WaSLb27!:cC2aSD2d,cJGY!:cC2aSD2d,c9Re5Fg8O:cC2aSD2d,W!G,l77s4IdDN,7mQDm8G/,j2DjA/,fK5cPMjO2,EcJQf7OjT2|mDAhQE,cA97<,fFCjNO24tf,EgILe8J,EtKT4<,EgOAgCQ,4kC,44cPM:,aABTaSL,k4QeRHmE0<,jENTm8G/,4,b5Sf8R,e8Sg8OmE0<,g4MT,bO1aA2hFG/,cPMlKFAb5SmJGqM2,bO1:fu7Ab5S,_4,cPMlKFAb5SaSD2d,bD0jNOcPM,nI4jO2cPM,hNHdJQcA9/qLV,gIO;m6Ff,bCDcACC7,cA9/qLV,cAC,hQ6N7:,j3La4Cb5S,CfFCA!(hNH),cM4bO1,nLLIcJQ7I<,nCJdJQk50b1J:mE0<,Ab5S7jNO,4f8RC,47cA9,kVKtKT,hNUcPMu7AbO1,c9Vk6AcEV<dF2!o<(hNH),c9V!S:f3Ke9J2Am8G:,c9VbBU/,kTB!GmE0<,m8Ga68mE0<(n8D),c9VfT3gOAI<(n8D),m8Ga68mE0<(mDA),n7BS,Il77Af2cPM4bO1,W7cA9,mDAd,cVGaT1jJQ7,gIOLG<,I!:2b0BkVD:mE0<,jNOcPM`g4A|hP7hQE,iEFT/,kVD!mE0<,fTChRA,hNHdJQhNUSA/��cJGe18fFC,jO2b66Ec8Q,aSF<,cGMS4:,CS/m6FmE0<,&qHG<,jJLtKT,bMBScPMA/mE0<,G,4t/aVSaT1/,nI4fK5/,nI4fK5/,f2S`ZmE0<,hK0b5S,dGLpFQ<,d0T!<,kVDkVD:<,cPBd0TcQJ,pGMkTB!<,e1Hb6F,m6PcEV<,c8QjO2bBU,f3K!tKT,dF2!`A/,Cm79mE0<,jO2S:b5SdF8cO7qLVmE0<@/dKDbAM,/\\+/fVBn06hMNdVQ,/\\./fVBn06hMNbER,/\\?/fVBn06hMNbER|/'/fVBn06hMN/ /n06hMN/\\[/cFAbER,/\\^/bER|/~/bER,/{/cFAbAM,/}/e0UbAM,/z/bAM|/y/bAM,/x/e0U|/w/e0U,/v/fVJ,/u/k8T$s2FdD5hJRbBJdVQdKDa1OoSRe4FdCThMFfUCdHGhSJfVBuDLb9JcFAe0UcQ3!aR7%dVP&a29/a5M0aSG1bGC2bL83hIG4e0G5g186hC27f758i3I9bER:fUB;gDJ<dLN=eEJ>qVDAdIOBi5CCt48Da2GEs2RFhIKGaSCHrSRIj4LJi66Ka2CLs4HMkGFNqOPOa5DPi11Qb9IRbFVSqV8Tb25Un7JVsBEWa38XdDMYbH5Zs04]bBA_bG0`mBJaqHMbt7Kci2Bdc8Pek4PfqEQga3CheBPidEIjhLLkbN4lhN5ma3Tne4Ros52pbDRqdBCru70srR3te0V01-20131231";
//-->;


;(function ($) {
     /*
     滑动插件封装(目前功能单调，具体样式要自己调)
     v1.0版
     */
    //左右滑动
    $.fn.ZJiaJuControlSwitch = function (options) {
        options = $.extend({
            leftBtn: "", //左移动按钮
            rightBtn: "", //右移动按钮
            marginleft: 24, //额外移动边距
            showLength: 5, //内容框显示元素的个数
            defendRightBgImg: null,
            allowLeftBgImg: null
        }, options || {});
        var s = "#";
        var contain = $(this);
        var rLeft = $(s + options.leftBtn);
        var rRight = $(s + options.rightBtn);
        var initRightBgImg = rRight.css("background");
        var initLeftBgImg = rLeft.css("background");
        var liList = contain.find("li");
         
        var allowLeftBgImg = options.allowLeftBgImg;
        var defendRightBgImg = options.defendRightBgImg;
        //右移动
        rRight.unbind().click(function () {
            var len = liList.length - options.showLength;
            if (len <= 0) {
                return false;
            }
            var liWidth = liList.width() + options.marginleft;
            var liWidths = -len * liWidth + "px";
            var liWidthLen = -(len - 1) * liWidth + "px";
            if (!contain.is(":animated")) {
                if (contain.css("margin-left") == liWidths) {
                    contain.stop();
                }
                else {
                    contain.animate({ marginLeft: "-=" + liWidth });
                    rRight.css({ "background": initRightBgImg, "background-image": initRightBgImg });
                    if (allowLeftBgImg != null && allowLeftBgImg != "") {
                        rLeft.css({ "background": allowLeftBgImg, "background-image": allowLeftBgImg });
                    }
                }
                if (contain.css("margin-left") == liWidthLen) {
                    if (defendRightBgImg != null && defendRightBgImg != "") {
                        rRight.css({ "background": defendRightBgImg, "background-image": defendRightBgImg });
                    }
                    if (allowLeftBgImg != null && allowLeftBgImg != "") {
                        rLeft.css({ "background": allowLeftBgImg, "background-image": allowLeftBgImg });
                    }
                  
                }
            }
        });
        //向左移动
        rLeft.unbind().click(function () {
            var liWidth = liList.width();
            if (liList.length <= 0) {
                return false;
            }
            var addWidth = liWidth + options.marginleft;
            var negWidth = "-" + addWidth + "px";
            if (!contain.is(":animated")) {
                if (contain.css("margin-left") == liWidth) {

                }
                if (contain.css("margin-left") == "0px") {
                    contain.stop();
                } else {
                    contain.animate({ marginLeft: "+=" + addWidth });
                    if (allowLeftBgImg != null && allowLeftBgImg != "") {
                        rLeft.css({ "background": allowLeftBgImg, "background-image": allowLeftBgImg });
                    }
                    rRight.css({ "background": initRightBgImg, "background-image": options.initRightBgImg });
                }
                if (contain.css("margin-left") == negWidth) {
                    if (options.allowLeftBgImg != null && options.allowLeftBgImg != "") {
                        rLeft.css({ "background": initLeftBgImg, "background-image": initLeftBgImg });
                        rRight.css({ "background": initRightBgImg, "background-image": options.initRightBgImg });
                    }
                }
            }
        });
    };

        /*改变省市区背景颜色更换*/
    $.fn.ZJiaJuAreaHover = function () {
            return this.each(function () {
                var selObject = $(this);//获取当前对象
                selObject.hover(
                    function () {
                        selObject.find(".btn_c").addClass("btn_c_h");
                    },
                    function () {
                        selObject.find(".btn_c").removeClass("btn_c_h");
                     
                    }
                );
            });
        };

    //上下滑动
    $.fn.ZJiaJuControlUpDownSwitch = function (options) {
        options = $.extend({
            leftBtn: "",  //左移动按钮
            rightBtn: "", //右移动按钮
            marginleft: 0,//额外移动边距
            showLength: 1//内容框显示元素的个数
        }, options);
        var s = "#";
        var contain = $(this);
        var rUp = $(s + options.leftBtn);
        var rDown = $(s + options.rightBtn);
        var liList = contain.find("li");
        //向上移动
        rUp.click(function () {
            var len = liList.length - options.showLength;
            if (len <= 0) return false;
            var liHeight = liList.height() + options.marginleft;
            var liHeights = -len * liHeight + "px";
            var liHeightLen = -(len - 1) * liHeight + "px";
            if (!contain.is(":animated")) {
                if (contain.css("margin-top") == liHeights) {
                    contain.stop();
                }
                else {
                    contain.animate({ marginTop: "-=" + liHeight });
                }
                if (contain.css("margin-top") == liHeightLen) {
                }
            }
        });
        //向下移动
        rDown.click(function () {
            var liHeight = liList.height();
            if (liList.length <= 0) return false;
            var addWidth = liHeight + options.marginleft;
            var negWidth = "-" + addWidth + "px";
            if (!contain.is(":animated")) {
                if (contain.css("margin-top") == "0px") {
                    contain.stop();
                } else {
                    contain.animate({ marginTop: "+=" + addWidth });
                }
                if (contain.css("margin-top") == negWidth) {
                }
            }
        });
    };

    //拖拽
    $.fn.ZJiaJuDrag = function() {
        var isMove = 0;
        var mouseLeft = 0;
        var mouseTop = 0;
        return $(this).bind("mousemove", function (e) {
            if (isMove == 1) {
                $(this).offset({ top: e.pageY - mouseLeft, left: e.pageX - mouseTop });
            }
        }).bind("mousedown", function (e) {
            isMove = 1;
            var offset = $(this).offset();
            mouseLeft = e.pageX - offset.left;
            mouseTop = e.pageY - offset.top;
        }).bind("mouseup", function () {
            isMove = 0;
        }).bind("mouseout", function () {
            isMove = 0;
        });
    };

    //ZJiaJu 信息提醒框
    //定义整个msgbox对象
    $.ZJiaJuMsgBox = $.ZJiaJuMsgBox || {};
    //定义默认属性值
    $.ZJiaJuMsgBox.defaults = {
        icon: 'ok', //图标(ok:成功信息,no:错误信息,warn:警告,clear:什么都没有)
        i18n: false, //是否使用国际化:如果是,message指定为code,否则message为要显示的值
        message: '', //同上
        timeOut: 3000, //多长时间之后消失
        beforeHide: null //消失前执行的方法
    };
    $.ZJiaJuMsgBox.show = function (p) {
        p = $.extend({}, $.ZJiaJuMsgBox.defaults, p || {});

        var f = {
            show: function () {
                var msgbox;
                var isFirst = true;        //是否是第一次显示
                if ($('.msgbox_layout_wrap').length > 0) {
                    msgbox = $('.msgbox_layout_wrap')[0];
                    isFirst = false;
                } else {
                    msgbox = $(f.getHtml());
                  //  f.loadCss("http://cdn.jiajia1.com/Content/msgbox/msgbox.css");
                }
                //设置top
                $('.msgbox_layout_wrap', msgbox).css('top', f.getTop() + 'px');
                //设置文本
                f.setText(f.getMessage(), msgbox);
                //设置图标
                f.setIcon(f.getStyle(), msgbox);
                //添加事件
                f.addEvent();

                //显示
                f.render(msgbox, isFirst);
            },
            //load css
            loadCss:function(url) {
                var fileref = document.createElement('link');
                fileref.setAttribute("rel", "stylesheet");
                fileref.setAttribute("type", "text/css");
                fileref.setAttribute("href", url);
                document.getElementsByTagName("head")[0].appendChild(fileref);
            },
            getMessage: function () {
                if (p.i18n) {
                    var message_ = $.util.getMessage(p.message);
                    if (!message_ || "" == message_)
                        message_ = p.message;

                    return message_;
                }
                return p.message;
            },
            getStyle: function () {
                var class_;
                if (p.icon != 'ok' && p.icon != 'no' && p.icon != 'warn' && p.icon != 'clear') {
                    class_ = "icon_clear";
                } else {
                    class_ = "icon_" + p.icon;
                }

                return class_;
            },
            getHtml: function () {
                var html = [];
                html.push('<div class="msgbox_layout_wrap" id="m_mgbox" style="">');
                html.push('     <span class="msgbox_layout" style="z-index: 10000;">');
                html.push('         <span class="" id="iconSpan"></span>');
                html.push('         <span id="txtPan"></span>');
                html.push('         <span class="icon_end"></span>');
                html.push('     </span>');
                html.push('</div>');

                return html.join("");
            },
            getTop: function () {
                //可视区域高度
                var viewHeight = $(window).height();
                return viewHeight - 27;
            },
            setText: function (text, msgbox) {
                var icon = $('#txtPan', $(msgbox));
                icon.html(text);
            },
            setIcon: function (iconStyle, msgbox) {
                var icon = $('#iconSpan', $(msgbox));
                var className = icon.attr('class');
                icon.removeClass(className);
                icon.addClass(iconStyle);
            },
            addEvent: function () {
                var inerval = setInterval(function () {
                    if (p.beforeHide) {
                        p.beforeHide();
                    }
                    $('#m_mgbox').hide();
                    clearTimeout(inerval);
                }, p.timeOut);
            },
            render: function (msgbox, isFirst) {
                if (isFirst) {
                    $('body').append(msgbox);
                } else {
                    $(msgbox).show();
                }
            }
        }

        f.show();
    }

})(jQuery);
;
(function ($) {
    $.fn.jCarouselLite = function (o) {
        o = $.extend({
            btnPrev: null,
            btnNext: null,
            btnGo: null,
            mouseWheel: false,
            auto: null,
            speed: 500,
            easing: null,
            vertical: false,
            circular: true,
            visible: 3,
            start: 0,
            scroll: 1,
            beforeStart: null,
            afterEnd: null
        },
        o || {});
        return this.each(function() {
            var b = false,
                animCss = o.vertical ? "top" : "left",
                sizeCss = o.vertical ? "height" : "width";
            var c = $(this),
                ul = $("ul", c),
                tLi = $("li", ul),
                tl = tLi.size(),
                v = o.visible;
            if (o.circular) {
                ul.prepend(tLi.slice(tl - v - 1 + 1).clone()).append(tLi.slice(0, v).clone());
                o.start += v;
            }
            var f = $("li", ul),
                itemLength = f.size(),
                curr = o.start;
            c.css("visibility", "visible");
            f.css({
                overflow: "hidden",
                float: o.vertical ? "none" : "left"
            });
            ul.css({
                margin: "0",
                padding: "0",
                position: "relative",
                "list-style-type": "none",
                "z-index": "1"
            });
            c.css({
                overflow: "hidden",
                position: "relative",
                "z-index": "2",
                left: "0px"
            });
            var g = o.vertical ? height(f) : width(f);
            var h = g * itemLength;
            var j = g * v;
            f.css({
                width: f.width(),
                height: f.height()
            });
            ul.css(sizeCss, h + "px").css(animCss, -(curr * g));
            c.css(sizeCss, j + "px");
            if (o.btnPrev)
                $(o.btnPrev).click(function() {
                    return go(curr - o.scroll);
                });
            if (o.btnNext)
                $(o.btnNext).click(function() {
                    return go(curr + o.scroll);
                });
            if (o.btnGo)
                $.each(o.btnGo,
                    function(i, a) {
                        $(a).click(function() {
                            return go(o.circular ? o.visible + i : i);
                        });
                    });
            if (o.mouseWheel && c.mousewheel)
                c.mousewheel(function(e, d) {
                    return d > 0 ? go(curr - o.scroll) : go(curr + o.scroll);
                });
            if (o.auto)
                setInterval(function() {
                        go(curr + o.scroll);
                    },
                    o.auto + o.speed);

            function vis() {
                return f.slice(curr).slice(0, v);
            };

            function go(a) {
                if (!b) {
                    if (o.beforeStart) o.beforeStart.call(this, vis());
                    if (o.circular) {
                        if (a <= o.start - v - 1) {
                            ul.css(animCss, -((itemLength - (v * 2)) * g) + "px");
                            curr = a == o.start - v - 1 ? itemLength - (v * 2) - 1 : itemLength - (v * 2) - o.scroll;
                        } else if (a >= itemLength - v + 1) {
                            ul.css(animCss, -((v) * g) + "px");
                            curr = a == itemLength - v + 1 ? v + 1 : v + o.scroll;
                        } else curr = a;
                    } else {
                        if (a < 0 || a > itemLength - v) return;
                        else curr = a;
                    }
                    b = true;
                    ul.animate(animCss == "left" ? {
                            left: -(curr * g)
                        } : {
                            top: -(curr * g)
                        },
                        o.speed, o.easing,
                        function() {
                            if (o.afterEnd) o.afterEnd.call(this, vis());
                            b = false;
                        });
                    if (!o.circular) {
                        $(o.btnPrev + "," + o.btnNext).removeClass("disabled");
                        $((curr - o.scroll < 0 && o.btnPrev) || (curr + o.scroll > itemLength - v && o.btnNext) || []).addClass("disabled");
                    }
                }
                return false;
            }

        });
    };
    function css(a, b) {
        return parseInt($.css(a[0], b)) || 0;
    };
    function width(a) {
        if (a.length>=1) {
            return a[0].offsetWidth + css(a, 'marginLeft') + css(a, 'marginRight');
        }
        return false;
    };
    function height(a) {
        if (a.length >= 1) {
            return a[0].offsetHeight + css(a, 'marginTop') + css(a, 'marginBottom');
        }
        return false;
    }
})(jQuery);;
