

export const BackEndHost = import.meta.env.VITE_BACKEND_HOST;
export const BackEndPort = import.meta.env.VITE_BACKEND_PORT;
export const backendUrl = BackEndHost != null ? `http://${BackEndHost}:${BackEndPort}/api` : "http://localhost:8080/api";


console.log("backendUrl: ", backendUrl);


console.log("BACKENDHOST", BackEndHost);
console.log("BackEndPort", BackEndPort);
