import useTokenStore from "@/stores/token";

const {token, getToken, setToken} = useTokenStore();
const stores = {
    token, getToken, setToken
}

export default stores;