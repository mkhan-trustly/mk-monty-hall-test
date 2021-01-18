import { useEffect, useState } from 'react';
import axios from 'axios';

export default function useBackendHealth() {
  const [backendHealth, setBackendHealth] = useState(undefined);

  function getHealth() {
    axios.get(`actuator/health`)
      .then(res => {
        if (res.status === 200) {
          setBackendHealth(res.data.status);
        } else {
          setBackendHealth('DOWN');
        }
      })
      .catch(res => {
        console.error(res);
        setBackendHealth('DOWN');
      });
  }

  useEffect(() => {
    getHealth();
    const interval = setInterval(getHealth, 10 * 1000);
    return () => {
      clearInterval(interval);
    }
  }, [])
  return backendHealth
}
