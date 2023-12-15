import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loginUserId: null,
    },
    mutations: {
        updateLoginUserId(state, loginUserId) {
            state.loginUserId = loginUserId;
        },
    },
    actions: {
        setLoginUserId({ commit }, loginUserId) {
            commit('updateLoginUserId', loginUserId);
        },
    },
});