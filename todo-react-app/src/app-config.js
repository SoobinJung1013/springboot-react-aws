let backendHost;

const hostname = window && window.location && window.location.hostname;

if (hostname === "localhost") {
  backendHost = "";
} else {
  backendHost = "";
}

export const API_BASE_URL = `${backendHost}`;
