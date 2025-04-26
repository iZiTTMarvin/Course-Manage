import stores from "@/stores";

export const baseURL = "/api";
export const baseIP = "127.0.0.1";
export const timeout = 10000;
export const headers = {
    // token: localStorage.getItem("token")?.slice(1, localStorage.getItem("token")?.length),
    // token: JSON.parse(localStorage.getItem("token")),
    // token: JSON.parse(localStorage.token),
    token: stores.token,
}
