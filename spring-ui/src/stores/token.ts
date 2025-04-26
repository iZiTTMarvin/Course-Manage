import {computed, ref} from 'vue'
import {defineStore} from 'pinia'

const useTokenStore = defineStore('token', () => {
    const token = ref(JSON.parse(localStorage.getItem('token')));
    const getToken = computed(() => token.value)

    function setToken(val: string) {
        token.value = val;
    }

    return {token, getToken, setToken};
})

export default useTokenStore;
