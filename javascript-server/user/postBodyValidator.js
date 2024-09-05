const { check } = require('express-validator');

module.exports = [
  check('itemId').not().isEmpty().withMessage('必須項目です。').isInt().withMessage('アイテムIDは数値を入力してください'),
  check('itemName').not().isEmpty().withMessage('必須項目です。'),
];
