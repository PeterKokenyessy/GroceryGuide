

function unitConverter(quantity,unit) {
    return unit === "g" && quantity >= 1000 ? quantity/1000 + " kg" :  quantity + " " + unit;
}

export default unitConverter