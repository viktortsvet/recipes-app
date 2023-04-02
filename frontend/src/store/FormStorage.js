const state = {
    dataForm: []
};

const getters = {
    dataForm: state => state.dataForm
};

const mutations = {
    set_dataForm: (state, param) => {
        state.dataForm = param;
    }
};

const actions = {
    setDataForm: (context, param) => {
        context.commit('set_dataForm', param);
    }
};

const namespaced = true;

export default {
    state, getters, mutations, actions, namespaced
}