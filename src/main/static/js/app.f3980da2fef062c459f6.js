webpackJsonp([1],{"+skl":function(t,e){},"0ZVF":function(t,e,s){t.exports=s.p+"static/img/flower.e2717fd.gif"},"2z7q":function(t,e,s){t.exports=s.p+"static/img/flowers.fde7ff6.gif"},"6RWj":function(t,e,s){t.exports=s.p+"static/img/animated-beetle-and-bug-image-0003.d55752f.gif"},FD9b:function(t,e){},KdGT:function(t,e,s){t.exports=s.p+"static/img/X-自定义.45b520b.gif"},MfKx:function(t,e,s){t.exports=s.p+"static/img/Coin.7bbd96d.png"},NHnr:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=s("7+uW"),a={name:"board",props:{},data:function(){return{labelTime:"",bank:null,urlHeader:"/api/"}},watch:{labelTime:function(t){this.$store.commit("setLabelTime",t)}},mounted:function(){console.log("mounted"),this.updateTimeTimerly(),this.loadData()},computed:{startTime:function(){return this.$store.getters.startTime},testdata:function(){return this.$store.getters.testdata},allCoinsCount:function(){return this.$store.state.AllCoinsCount},fortune:function(){return this.$store.state.Fortune}},methods:{loadData:function(){var t=this,e=this.urlHeader+"bank/stat";$.ajax({url:e,success:function(e){t.$store.commit("setFortune",e)}})},updateTimeTimerly:function(){var t=this;setInterval(function(){t.updatelabelTime()},1e3)},updatelabelTime:function(){var t=Date.parse(this.startTime),e=((new Date).getTime(),((new Date).getTime()-t)/1e3),s=Math.floor(e/60),i=Math.floor(e-60*s);i<10&&(i="0"+i),this.labelTime=s+":"+i}}},n={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"easyui-panel",staticStyle:{margin:"0",overflow:"hidden"},attrs:{id:"header","data-options":"fit:true"}},[i("router-link",{attrs:{to:"/"}},[i("div",{staticStyle:{float:"left",width:"35%"},attrs:{id:"progress"}},[i("img",{attrs:{id:"imgpass",src:s("0ZVF")}}),t._v(" "),i("div",{staticClass:"image_note",staticStyle:{color:"lightgreen"},attrs:{id:"LblCountPass"}},[t._v("\n        "+t._s(null==t.testdata?"":t.testdata.PassCount)+"\n      ")]),t._v(" "),i("img",{attrs:{id:"imgfail",src:s("6RWj")}}),t._v(" "),i("div",{staticClass:"image_note",staticStyle:{color:"red"},attrs:{id:"LblCountFail"}},[t._v("\n        "+t._s(null==t.testdata?"":t.testdata.FailCount)+"\n      ")]),t._v(" "),i("img",{attrs:{src:s("2z7q")}}),t._v(" "),i("div",{staticClass:"image_note",staticStyle:{color:"blue"},attrs:{id:"LblCountMark"}},[t._v("\n        "+t._s(null==t.testdata?"":t.testdata.CentCount)+"\n      ")])])]),t._v(" "),i("router-link",{attrs:{to:"/Fortune/"}},[i("div",{attrs:{id:"money"}},[i("img",{attrs:{src:s("UFGD")}}),t._v(" "),i("div",{staticClass:"image_note",attrs:{id:"lblCoinTheRun"}},[t._v("\n          "+t._s(t.fortune.AllCoinsCount)+"\n      ")]),t._v(" "),i("img",{attrs:{src:s("MfKx")}}),t._v(" "),i("div",{staticClass:"image_note",attrs:{id:"lblCoin"}},[t._v("\n         "+t._s(t.fortune.CoinCntToday)+"\n      ")]),t._v(" "),i("img",{attrs:{src:s("NICe")}}),t._v(" "),i("div",{staticClass:"image_note",attrs:{id:"lblAllCoins"}},[t._v("\n        "+t._s(t.fortune.CoinCntLeft)+"\n      ")])])]),t._v(" "),i("div",{staticStyle:{float:"right",height:"48px"},attrs:{id:"clock"}},[i("span",{staticClass:"image_note",staticStyle:{color:"white"},attrs:{id:"TimeTopmost"}},[t._v("\n      "+t._s(null==t.testdata?"":t.testdata.TopmostSeconds)+"\n    ")]),t._v(" "),i("span",{staticClass:"image_note",staticStyle:{color:"blue"},attrs:{id:"labelTime"}},[t._v("\n      "+t._s(null==t.testdata?"":t.labelTime)+"\n    ")])])],1)},staticRenderFns:[]};var o=s("VU/8")(a,n,!1,function(t){s("e8sU")},"data-v-7240cc36",null).exports,r={components:{Board:o},name:"App"},l={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("Board"),this._v(" "),e("router-view")],1)},staticRenderFns:[]};var u=s("VU/8")(r,l,!1,function(t){s("h0wB")},null,null).exports,d=s("/ocq"),c=s("mvHQ"),m=s.n(c),v={props:{isShown:{type:Boolean,default:!1},isTopmost:{type:Boolean}},data:function(){return{my_isShown:!1}},watch:{isShown:function(t){t&&(this.my_isShown=t)},my_isShown:function(t){if(t){var e=this.isTopmost?$("#FormNewCoinTopmost_New"):$("#formNewCoin_New");this.popUpMe(e),this.isTopmost?$("#audioTopmost")[0].play():$("#audioNewCoin")[0].play()}}},name:"EndSplash",mounted:function(){},beforeDestroy:function(){console.log("*** beforeDestroy ***")},methods:{newCoinClick:function(){this.my_isShown=!1,$("#container").fadeOut(1e3),this.isTopmost?$("#audioTopmost")[0].pause():$("#audioNewCoin")[0].pause(),this.$emit("pop_closed"),console.log("test done")},popUpMe:function(t){t.show();var e=t.find("div:first-child");e.css("opacity",100),this.flashBackground(e);var s=t.find("img");s.width(0),s.height(0),s.animate({width:$(window).width()/2,height:$(window).height()/2},2e3)},flashBackground:function(t){var e=this;this.my_isShown?(console.log("flashBackground--\x3e"),console.log(t.css("opacity")),t.animate({opacity:50},3e3).animate({opacity:0},3e3),setTimeout(function(s){e.flashBackground(t)},6e3)):console.log("stop")}}},p={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{directives:[{name:"show",rawName:"v-show",value:t.isShown,expression:"isShown"}],attrs:{id:"container"}},[t._m(0),t._v(" "),t._m(1),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:t.isTopmost,expression:"isTopmost"}],staticClass:"my_pop_win",attrs:{id:"FormNewCoinTopmost_New"},on:{click:t.newCoinClick}},[s("div"),t._v(" "),t._m(2)]),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:0==t.isTopmost,expression:"isTopmost==false"}],staticClass:"my_pop_win",attrs:{id:"formNewCoin_New"},on:{click:t.newCoinClick}},[s("div"),t._v(" "),t._m(3)])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("audio",{attrs:{id:"audioNewCoin",loop:"loop"}},[e("source",{attrs:{src:s("OO5N"),type:"audio/wav"}})])},function(){var t=this.$createElement,e=this._self._c||t;return e("audio",{attrs:{id:"audioTopmost"}},[e("source",{attrs:{src:s("PJLA"),type:"audio/wav"}})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("img",{attrs:{src:s("THZM")}})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("img",{attrs:{src:s("P4gX")}})])}]};var f={components:{EndSplash:s("VU/8")(v,p,!1,function(t){s("FD9b")},"data-v-74c56360",null).exports,Board:o},name:"Tester",data:function(){return{testdata:{},my_answer:"",splash_shown:!1,splash_isTopmost:!1,startTime:new Date,testRecs:[],log:"to do...",urlHeader:"/api/",lblLast:"",IsAutoSubmit:!0,curindex:0,QuestionLast_IsPass:!0}},mounted:function(){try{var t=this.$store.getters.testdata;null==t?(this.log="mydata==null",this.loadTestData(),this.log+="after loadTestData"):(this.log+="mydata!=null",this.testdata=t)}catch(t){this.log="mydata==null"}},watch:{my_answer:function(t){console.log(t),this.tryAutoSubmit()},testdata:function(t){this.$store.commit("setTestData",t)},startTime:function(t){null!=t&&this.$store.commit("setStartTime",t)}},computed:{GetDoneQuestions:function(){return this.testdata.Questions.filter(function(t){return null!=t.IsPass})},ComputeStyle4Last:function(){return 1==this.QuestionLast_IsPass?{"background-color":"green"}:{"background-color":"red"}}},methods:{onNumberkeyClick:function(){var t=$(event.target).text();this.my_answer+=t},onInputKeydown:function(){var t=event.keyCode;return 13==t?(this.onSubmit(),!0):8==t?(this.IsAutoSubmit=!1,!0):t>=48&&t<=57||t>=96&&t<=105?void 0:123==t?void console.log("F12 press"):($("#audioError")[0].play(),!1)},onDelClick:function(){this.my_answer="",this.IsAutoSubmit=!1},onSubmit:function(){if(0!=this.my_answer.length){var t=this.testdata.Questions[this.curindex],e=t.Answer==this.my_answer;t.IsPass=e,e?(this.testdata.CentCount++,this.testdata.PassCount++):(this.testdata.CentCount--,this.testdata.PassCount++),this.QuestionLast_IsPass=e,this.lblLast=t.Ask+" = "+this.my_answer,e||(this.lblLast+=" ("+this.my_answer+")"),this.testdata.CentCount<this.testdata.MyRound&&this.curindex<=this.testdata.Questions.length-2?(this.curindex++,e?$("#audioPassRight")[0].play():$("#audioPassError")[0].play(),this.my_answer="",$("#my_answer").focus(),this.IsAutoSubmit=this.testdata.IsAutoSubmit):this.callEndTest()}else $("#audioError")[0].play()},callEndTest:function(){var t=this,e=this.urlHeader+"quizzesex",s=m()(this.testdata);$.ajax({type:"POST",xhrFields:{withCredentials:!0},data:s,dataType:"json",contentType:"application/json",url:e,success:function(e){t.splash_isTopmost=e,t.splash_shown=!0}})},tryAutoSubmit:function(){if(this.IsAutoSubmit){var t=this.my_answer;0!=t.length&&this.testdata.Questions[this.curindex].Answer==t&&this.onSubmit()}},onPopClosed:function(){this.splash_shown=!1,console.log("onPopClosed"),this.loadTestData()},loadTestData:function(){var t=this,e=this.urlHeader+"quizzesex/0";$.ajax({xhrFields:{withCredentials:!0},url:e,success:function(e){t.testdata=e,t.curindex=0,t.lblLast="",t.QuestionLast_IsPass=!0,t.IsAutoSubmit=e.IsAutoSubmit,t.startTime=new Date,t.resetUI()},error:function(t,e,s){}});var s=this.urlHeader+"bank/stat";$.ajax({url:s,success:function(e){t.$store.commit("setFortune",e)}})},resetUI:function(){this.lblLast="",this.my_answer="",$("#my_answer").focus(),$(".my_pop_win").hide()}}},h={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("EndSplash",{attrs:{isTopmost:t.splash_isTopmost,isShown:t.splash_shown},on:{pop_closed:t.onPopClosed}}),t._v(" "),i("div",{attrs:{id:"cc"}},[i("div",{attrs:{id:"topbar"}},[i("div",{attrs:{id:"area-question"}},[i("div",{staticClass:"topnumber",attrs:{id:"ask"}},[t._v("\n          "+t._s(void 0!=t.testdata.Questions?t.testdata.Questions[t.curindex].Ask:"")+"\n        ")]),t._v(" "),i("div",{staticClass:"topnumber",attrs:{id:"operator"}},[t._v("\n          =\n        ")]),t._v(" "),i("div",{staticClass:"topnumber",attrs:{id:"answer"}},[i("input",{directives:[{name:"model",rawName:"v-model",value:t.my_answer,expression:"my_answer"}],attrs:{id:"my_answer",type:"text"},domProps:{value:t.my_answer},on:{keydown:t.onInputKeydown,input:function(e){e.target.composing||(t.my_answer=e.target.value)}}})])]),t._v(" "),i("div",{attrs:{id:"area_submit"}},[i("div",{style:t.ComputeStyle4Last,attrs:{id:"lblLast"}},[t._v("\n          "+t._s(t.lblLast)+"\n        ")]),t._v(" "),i("div",{staticStyle:{float:"left",width:"40%"}},[i("input",{attrs:{type:"submit",id:"submit",value:"提交"},on:{click:t.onSubmit}}),t._v(" "),i("button",{attrs:{id:"del"},on:{click:t.onDelClick}},[t._v("删除")])])])]),t._v(" "),i("div",{attrs:{id:"layout_west"}},[null!=t.testdata&&null!=t.testdata.Questions?i("div",{attrs:{id:"MyTaskImageList"}},t._l(t.GetDoneQuestions,function(t){return i("div",[i("div",{staticClass:"myicon"},[i("a",{attrs:{href:"#",title:t.Ask+" = "+t.Answer+"; "+t.TesterAnswer}},[t.IsPass?i("img",{staticClass:"imgRec",attrs:{id:"model_imgpass",src:s("0ZVF")}}):i("img",{staticClass:"imgRec",attrs:{id:"model_imgfail",src:s("6RWj")}})])])])})):t._e()]),t._v(" "),i("div",{attrs:{id:"layout_center"}},[i("div",{attrs:{id:"numberpad"}},[i("div",[i("button",{on:{click:t.onNumberkeyClick}},[t._v("7")]),t._v(" "),i("button",{on:{click:t.onNumberkeyClick}},[t._v("8")]),t._v(" "),i("button",{on:{click:t.onNumberkeyClick}},[t._v("9")])]),t._v(" "),i("div",[i("button",{on:{click:t.onNumberkeyClick}},[t._v("4")]),t._v(" "),i("button",{on:{click:t.onNumberkeyClick}},[t._v("5")]),t._v(" "),i("button",{on:{click:t.onNumberkeyClick}},[t._v("6")])]),t._v(" "),i("div",[i("button",{on:{click:t.onNumberkeyClick}},[t._v("1")]),t._v(" "),i("button",{on:{click:t.onNumberkeyClick}},[t._v("2")]),t._v(" "),i("button",{on:{click:t.onNumberkeyClick}},[t._v("3")])]),t._v(" "),i("div",[i("button",{attrs:{id:"numkey_last"},on:{click:t.onNumberkeyClick}},[t._v("0")])])])])]),t._v(" "),t._m(0),t._v(" "),t._m(1),t._v(" "),t._m(2)],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("audio",{attrs:{id:"audioError"}},[e("source",{attrs:{src:s("eDuN"),type:"audio/wav"}})])},function(){var t=this.$createElement,e=this._self._c||t;return e("audio",{attrs:{id:"audioPassError"}},[e("source",{attrs:{src:s("Z1Fc"),type:"audio/wav"}})])},function(){var t=this.$createElement,e=this._self._c||t;return e("audio",{attrs:{id:"audioPassRight"}},[e("source",{attrs:{src:s("xb70"),type:"audio/wav"}})])}]};var _=s("VU/8")(f,h,!1,function(t){s("W1bV"),s("ROiq")},"data-v-136194b8",null).exports,g={props:["showme","bank"],data:function(){return{userDefCoinNumber:1,userDefCoinNote:"",my_isShown:!1,dlgConfirmPara:{visible:!1},urlHeader:"/api/"}},watch:{showme:function(t){t&&(this.my_isShown=t)}},computed:{},mounted:function(){},methods:{getUri:function(t){return"http://10.0.0.2/"+t},getProductByPrice:function(t){return this.bank.Products.filter(function(e){return e.Price==t})},selectProduct:function(t,e){this.dlgConfirmPara.visible=!0,this.dlgConfirmPara.price=t,this.dlgConfirmPara.name=e},onSubmitUserDef:function(){this.dlgConfirmPara.visible=!0,this.dlgConfirmPara.price=this.userDefCoinNumber,this.dlgConfirmPara.name=this.userDefCoinNote},ok:function(){this.SubmitCostData(this.dlgConfirmPara.price,this.dlgConfirmPara.name),this.dlgConfirmPara.visible=!1},cancel:function(){this.dlgConfirmPara.visible=!1},SubmitCostData:function(t,e){var s=this,i={price:t,productName:e},a=this.urlHeader+"bank/buy";$.ajax({url:a,data:i,dataformat:"json",success:function(t){console.log(t),"ok"!=t?s.$Message.warning(t):(s.$Message.info("已消费"+s.dlgConfirmPara.price+"个金币"+s.dlgConfirmPara.name),s.$emit("event_spent"))},error:function(){alert("失败:"+a)}})},onClose:function(){this.my_isShown=!1,this.$emit("pop_closed")}}},C={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{directives:[{name:"show",rawName:"v-show",value:t.my_isShown,expression:"my_isShown"}],attrs:{id:"products"}},[s("Modal",{attrs:{title:"消费确认"},on:{"on-ok":t.ok,"on-cancel":t.cancel},model:{value:t.dlgConfirmPara.visible,callback:function(e){t.$set(t.dlgConfirmPara,"visible",e)},expression:"dlgConfirmPara.visible"}},[s("p",[t._v("请确认：")]),t._v(" "),s("p",[t._v("使用"+t._s(t.dlgConfirmPara.price)+"个金币 ")]),t._v(" "),s("p",[t._v(t._s(t.dlgConfirmPara.name)+" ")])]),t._v(" "),s("div",{attrs:{id:"close_button"},on:{click:t.onClose}},[t._v("\n    X\n  ")]),t._v(" "),s("div",{attrs:{id:"content"}},[null!=t.bank?s("div",{attrs:{id:"tabsGoods"}},t._l(t.bank.PriceList,function(e){return s("div",{staticClass:"price_area"},[s("div",{staticClass:"caption"},[t._v(" "+t._s(e)+"个金币")]),t._v(" "),s("div",{staticClass:"price_imglist"},t._l(t.getProductByPrice(e),function(e){return s("div",{staticClass:"DivProduct"},[s("img",{staticClass:"ImgCoin",attrs:{src:t.getUri(e.Url)},on:{click:function(s){t.selectProduct(e.Price,e.Name)}}})])}))])})):t._e(),t._v(" "),s("div",{attrs:{id:"price_userDef"}},[s("div",{staticClass:"caption"},[t._v(" N个金币")]),t._v(" "),s("div",[t._m(0),t._v(" "),s("div",{staticClass:"DivCoinUserDefForm"},[s("form",[s("div",{staticStyle:{width:"100%"}},[s("span",[t._v("金币数:")]),t._v(" "),s("span",[s("select",{directives:[{name:"model",rawName:"v-model",value:t.userDefCoinNumber,expression:"userDefCoinNumber"}],attrs:{id:"usedefPrice"},on:{change:function(e){var s=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){return"_value"in t?t._value:t.value});t.userDefCoinNumber=e.target.multiple?s:s[0]}}},t._l(20,function(e){return s("option",[t._v(t._s(e))])}))]),t._v(" "),s("span",[t._v("用途:")]),t._v(" "),s("input",{directives:[{name:"model",rawName:"v-model",value:t.userDefCoinNote,expression:"userDefCoinNote"}],attrs:{id:"usage"},domProps:{value:t.userDefCoinNote},on:{input:function(e){e.target.composing||(t.userDefCoinNote=e.target.value)}}}),t._v(" "),s("input",{attrs:{id:"btn",type:"submit",value:"提交"},on:{click:function(e){return e.stopPropagation(),e.preventDefault(),t.onSubmitUserDef(e)}}})])])])])])])],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"DivCoinUserDef"},[e("img",{staticClass:"ImgCoin",attrs:{src:s("KdGT")}})])}]};var y={components:{Products:s("VU/8")(g,C,!1,function(t){s("kiST")},"data-v-254561af",null).exports},data:function(){return{bank:null,product_isShown:!1,urlHeader:"/api/"}},watch:{bank:function(t){this.$store.commit("setFortune",t)}},mounted:function(){this.loadData()},methods:{loadData:function(){var t=this,e=this.urlHeader+"bank/stat";$.ajax({url:e,success:function(e){t.bank=e}})},onClickCoin:function(){this.product_isShown=!0},onPopClose:function(){this.product_isShown=!1},onEventSpent:function(){this.loadData()},getUri:function(t){return"http://10.0.0.2//"+t}}},b={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{attrs:{id:"container"}},[i("div",{attrs:{id:"up"}},[null!=t.bank?i("div",{attrs:{id:"MyCoinImageList"}},t._l(t.bank.CoinCntLeft,function(e){return i("div",{staticClass:"DivCoin",on:{click:t.onClickCoin}},[i("img",{staticClass:"ImgCoin",attrs:{src:s("P4gX")}})])})):t._e()]),t._v(" "),i("Products",{attrs:{bank:t.bank,showme:t.product_isShown},on:{pop_closed:t.onPopClose,event_spent:t.onEventSpent}}),t._v(" "),i("div",{attrs:{id:"down"}},[null!=t.bank?i("div",{staticStyle:{padding:"5px",background:"#eee"},attrs:{id:"cart","data-options":"region:'center',title:'购物车'"}},t._l(t.bank.ProductBuyToday,function(e){return i("div",{staticClass:"DivProductTodayBuy"},[i("a",{staticClass:"easyui-tooltip",attrs:{href:"#",title:e.Name}},[i("img",{staticClass:"ImgCoin easyui-tooltip",attrs:{src:t.getUri(e.Url),width:"291",height:"290"}})])])})):t._e()])],1)},staticRenderFns:[]};var j=s("VU/8")(y,b,!1,function(t){s("y3Et")},"data-v-601de19c",null).exports;i.default.use(d.a);var w=new d.a({routes:[{path:"/",name:"Tester",component:_},{path:"/Fortune",name:"Fortune",component:j}]}),R=(s("4kSj"),s("NYxO"));i.default.use(R.a);var k=new R.a.Store({state:{testdata:null,labelTime:"",startTime:"",AllCoinsCount:"",Fortune:{}},getters:{testdata:function(t){return t.testdata},labelTime:function(t){return t.labelTime},startTime:function(t){return t.startTime}},mutations:{setTestData:function(t,e){t.testdata=e},setLabelTime:function(t,e){t.labelTime=e},setStartTime:function(t,e){t.startTime=e},setFortune:function(t,e){t.Fortune=e}}}),x=s("BTaQ"),G=s.n(x);s("+skl");i.default.use(G.a),i.default.config.productionTip=!1,new i.default({el:"#app",store:k,router:w,components:{App:u},template:"<App/>"})},NICe:function(t,e,s){t.exports=s.p+"static/img/AllCoins.45a59d7.png"},OO5N:function(t,e,s){t.exports=s.p+"static/media/newcoin.29afbcf.wav"},P4gX:function(t,e,s){t.exports=s.p+"static/img/Coins-88531.2cc87be.gif"},PJLA:function(t,e,s){t.exports=s.p+"static/media/鼓掌声1.8bf9132.wav"},ROiq:function(t,e){},THZM:function(t,e,s){t.exports=s.p+"static/img/timgKUNDSTL1.0c41581.gif"},UFGD:function(t,e,s){t.exports=s.p+"static/img/CoinTheRun.c539af1.png"},W1bV:function(t,e){},Z1Fc:function(t,e){t.exports="data:audio/x-wav;base64,UklGRiUHAABXQVZFZm10IBAAAAABAAEAESsAABErAAABAAgAZGF0YQEHAAB0XYxpdIBpgF2AgHSMaYB0gICAgJd0dIyMjJd0dICAgJeMjJeAdICAjIyAgIBpgJeMdGmAgIB0dIB0gIBpdGmAgHR0aXR0jGldaXRdaWldjHSAgHRpdHSAjIB0l4yAdIyMjICAgKOMjGldaWldl4B0aWl0aYB0gHSMgIB0jKOAdICMdICAdIyXaWlpjIBdXXRpjHSMl3R0gJdpaXSMdF1pgGmAjFF0jF1pdHSMXWl0dICMaXSMl4Bpl6OAjHSAl1GMdIxpgIB0dJeAdJeAXWmMRl2MXV1po2ldrzp0jEaMdJdpjIxpjKNpl4BdgIBpgHRpuoB0jLp0gIxpjF2jUYyjOqNGgHRRo2mMaXSjRq9RrzqXUYxpjHRpo0a6UaN0r2mAo3SXUbo6oy+vL5ddaYBRaVGXRpdGjFGXaZdRr1GvXaNdl13GRrqMdICXaa9Rxl2MdHSXRoyMabpGul1plzrGOro6aaNGryOvXXSXaYxGgEavUVGvI8ZRaYxdul1dry+jdHSvF6+jI6OMRsZdaYxGjK9daaMvr4wvxrovr5dGxlF03UZ00TpRxjpd0V10xlF0o1GAly+M0TqAujqAr11pr110xl1dxmk6uoxRjKNpjLpdXa9dRqNpRqOMUYyMjLpdL6O6OjqvoyNGuoA6l68vOq+jOjqvr0Y6o6MjRsaXOnS6lyM6r5cjRq+jRkbGui8jurovI6PdXReA0YAjad26UUavxlEjo/SMF1HG0VEvgLqARoDRr0ZGr8ZpOozpoyMvl+mvUTp0o4BGRoyjaTp00ZcvL6Pply8jjNGXIyOAuoAjI4zdrzojadHGaS9Rr+mvRiOA6dFpFzqj0aNRL12Xl2kvOoy6gCMMUbrpr1EMOqPRo0YXRqPdr1EjXbrpxl0jOozd3ZdGL2nG6bpdFy+A3d2XLww6l93GdC86dK+6gC8XUaPGr10XL4DR3a90RkZ0l6OAXUZGaZejgF1GXWmMl4xpXXSMo5eMdFFRdKO6o2kvI1Gj0dGXUS86dLrRuoxdUXSvxqN0Ri9GgLrGo2k6I0aMxsaMRhcjXaPd3bp0OiMvaa/RxpddLy9Rl8bRr2kvIzppl7qvjF1GOlFpjK+jjGlRUWmMr6+jdFE6RnSv0dGjaTojL2mjxtGvgFEvOl2XxtGvjFEvL12Xxt26gEYXFzp0r8a6l2k6Iy9RjLrGr4xRIyM6aaPG0bqMUS8jRoCv3d26gFEvLzppjK+6o4BpUVFpjK+vo4xpUVFpjKO6uq+AXTo6UXSjxtGvgEYXDCNRgLrd3caXaTojL1F0o7q6r4xpOiMjOmmXusa6o3RRLy9GaZfG0dGvgFEvIy9GdKPG0dG6l2k6IyNGaZe60dGvjFEvIyM6aZe60dG6l2k6IyMvUYCv0dHGo3RGIxcjRnSjxt3RxqN0Ri8vOl2Ar8bRxqOAUSMXIzppjLrR0bqjgFEvIyM6UYCjxtHGr4xpRjo6Rl10o7rGxrqXdFE6OkZdgKO6xrqvl4BpUUZGUWmAo6+6r5eAXTovLzpRaYCXr7q6r5eAXVFGOkZRaYCXo6Ojl4B0UUY6LzpRaYyjusbGuqOMaVFGOjpRXYCXusbGuq+XdF1RRjpGUWmAl6Ojo5eMgHRdUVFRXWl0jJejr6+jjHRpUUZGRlFdgJevusa6uqOAaUY6Ly86UWmAo7rGxrqvl4BpUTovIy9GXXSMr7rRxrqvl4xpUS8jFyM6UWmMo7rGxsavo4x0XUY6OjpRXXSMo7rGxsa6r4x0UTovIyM6Rl1pjKOvusbGuqOMdFE6IyMjL0ZddIyjusbR0cavo4BpUTovLy9GXWmAl6+6xsbGuqOMdGlRRjo6OlFddIyjr8bRxrqjjHRdRi8jIyMvOlFpgJejusbGxrqjl4BpUUY6Ly8vOlFpgJejusbRxsa6o4yAaVE6Ly8vL0ZRaYCXr7rGxrq6r6OMgGldRjo6Ly86UV10jKOvusbGxrqjl4B0XVE6Ly8vOlFdaYCXo7q6xsbGuq+XjHRdUTovLy8vOkZdaYCXr7q6xsa6r6OXjHRpUUY6Ly8vOjpGXWmAl6Ovurq6r6+jl4B0XVFGOjo6RkZRXWl0jJejurq6urqvr6OXgHRdUUZGOkZGUV1pdICXo6+6urqvr6OXjIBpXVFGRjo6RkZRXWl0gIyjr7q6urqvr6OXjIBpXVE6OjovLzpGUVFddICMl6Ovurq6uq+vo5eAdGldRkY6Ly86OkZRXXSAjJejr7q6usa6r6OXl4B0aV1RRkY6Ojo6RlFdaXSAl6Ojr7rGurq6r6OXjIB0aV1RRjo6OkZGUVFdaYCMjJejr6+vr6+vo5eMgHRpXVFGRjo6OkZGUVFdaXSAjJejr6+6urq6uq+vo5eMgHRpXVFGRjo6Og=="},e8sU:function(t,e){},eDuN:function(t,e,s){t.exports=s.p+"static/media/error.c43f538.wav"},h0wB:function(t,e){},kiST:function(t,e){},xb70:function(t,e,s){t.exports=s.p+"static/media/Speech On.7b68130.wav"},y3Et:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.f3980da2fef062c459f6.js.map