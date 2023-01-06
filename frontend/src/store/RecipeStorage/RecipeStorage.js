const state = {
    recipes: []
};

const getters = {
    recipes: state => state.recipes,
    recipesForForm: (state) => (index) => {
        if (index !== null) {
            const item = state.recipes[index];
            return [
                {

                }
            ];
        }
    }
};