const { body } = require('express-validator');

module.exports = [
  body('user_name').not().isEmpty().withMessage('必須項目です。'),
  body('first_name').not().isEmpty().withMessage('必須項目です。'),
  body('last_name').not().isEmpty().withMessage('必須項目です。'),
  body('password').not().isEmpty().withMessage('必須項目です。'),
  body('user_status').not().isEmpty().withMessage('必須項目です。').isInt().withMessage('アイテムIDは数値を入力してください'),
];
