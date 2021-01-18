export function isEmpty(obj) {
  return !obj || (Array.isArray(obj) ? obj.length === 0 : Object.keys(obj).length === 0);
}

export function hasErrors(obj) {
  return obj && obj['errorMsg'];
}

export function hasValidResult(obj) {
  return obj && Object.keys(obj).length > 0 && !hasErrors(obj);
}

export function getAsQueryParams(event) {
  const formData = new FormData(event.target);
  return new URLSearchParams(formData).toString();
}
